"""
NOTE: THIS WHOLE FILE IS MADE BY CHATGPT, NOT MY OWN WORK

Competitive Programming Test Harness
-----------------------------------

This script automates the testing of competitive programming solutions by running
test cases with standard input/output and comparing against expected outputs.

Features:
  - Supports any executable or command line program (C++, Java, Python, etc.)
  - Reads test inputs from `.in` files and expected outputs from `.out` files
  - Measures execution time and peak memory usage per test
  - Supports colored terminal output with pass/fail status
  - Generates detailed HTML reports with collapsible diffs and output views
  - Optional strict mode kills the tested program if it exceeds time or memory limits
  - Multi-threaded execution for faster testing

Test File Format:
  - Input files named `1.in`, `2.in`, ..., with corresponding expected outputs `1.out`, `2.out`, ...
  - Outputs must exactly match, including whitespace, for a test to pass

Usage:
  python3 TestHarness.py -e ./solution
  python3 TestHarness.py -e "java Solution" --test-dir tests/ --html --log

Command Line Options:
  -e, --exe          Path or command to run the solution (default: ./solution)
  -q, --quiet        Only show failed tests in terminal output
  -l, --log          Save plain text log to '.loghtml/test_log.txt'
  --html             Generate a pretty HTML diff log with clickable collapsible sections in '.loghtml/'
  --test-dir         Directory containing test `.in` and `.out` files (default: current directory)
  --time-limit       Float seconds; per-test time limit (used with --strict)
  --mem-limit        Float MB; per-test memory limit (used with --strict)
  --strict           If enabled, forcibly kills tests exceeding time or memory limits and reports as failure
  --src-code         Path to source code file to embed once in HTML and log

Notes:
  - Tested program must read from standard input and write to standard output.
  - HTML report includes collapsible sections for input, expected output, actual output, and diffs.
  - Without `--strict`, time and memory limits are measured but do not kill the tested program.
  - Multi-threading speeds up running multiple tests in parallel.
  - Requires Python 3.x and dependencies: `psutil`, `colorama`

Platform Support:
  - macOS, Linux, Windows

Example:
  python3 TestHarness.py -e ./solution --test-dir tests --time-limit 1.0 --mem-limit 256 --strict --html --log --src-code solution.cpp

"""

import subprocess
import glob
import time
import psutil
from concurrent.futures import ThreadPoolExecutor
import difflib
import argparse
import re
import os
from datetime import datetime
import platform
from colorama import init, Fore, Style

init(autoreset=True)

ansi_escape = re.compile(r'\x1b\[[0-9;]*m')
def strip_ansi(text):
    return ansi_escape.sub('', text)

def run_test(infile, exe_path, time_limit=None, mem_limit=None, strict=False):
    outfile = infile.replace(".in", ".out")

    with open(infile, 'r') as fin:
        start = time.perf_counter()
        process = subprocess.Popen(exe_path, stdin=fin, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        pid = process.pid
        proc = psutil.Process(pid)
        max_mem = 0
        exceeded_time = False
        exceeded_mem = False

        while process.poll() is None:
            try:
                mem = proc.memory_info().rss
                max_mem = max(max_mem, mem)
            except psutil.NoSuchProcess:
                break
            elapsed = time.perf_counter() - start

            if strict and time_limit is not None and elapsed > time_limit:
                process.kill()
                exceeded_time = True
                break

            if strict and mem_limit is not None and (max_mem / (1024 * 1024)) > mem_limit:
                process.kill()
                exceeded_mem = True
                break

            time.sleep(0.005)

        stdout, _ = process.communicate()
        end = time.perf_counter()
        time_ms = (end - start) * 1000
        max_mem_mb = max_mem / (1024 * 1024)

    output = stdout.strip()

    if not os.path.exists(outfile):
        expected = None
    else:
        with open(outfile, 'r') as f:
            expected = f.read().strip()

    passed = False
    status = "Unknown"

    if exceeded_time:
        status = "TLE"
    elif exceeded_mem:
        status = "MLE"
    elif expected is None:
        status = "No .out file"
    else:
        passed = (output == expected)
        status = "PASS" if passed else "WA"

    return {
        'file': infile,
        'passed': passed,
        'status': status,
        'expected': expected,
        'output': output,
        'time_ms': time_ms,
        'max_mem_mb': max_mem_mb,
        'input': None,
    }

def generate_html(results, filename, src_code=None):
    html = []
    html.append("<!DOCTYPE html><html><head><meta charset='utf-8'>")
    html.append("<style>")
    html.append("body { font-family: monospace; padding: 20px; background: #fff; color: #000; }")
    html.append(".pass { color: green; font-weight: bold; }")
    html.append(".fail { color: red; font-weight: bold; }")
    html.append(".tle { color: orange; font-weight: bold; }")
    html.append(".mle { color: purple; font-weight: bold; }")
    html.append(".noout { color: gray; font-weight: bold; }")
    html.append("details { margin-top: 1em; }")
    html.append("summary { cursor: pointer; }")
    html.append("table.diff { width: 100%; border-collapse: collapse; }")
    html.append("table.diff th, table.diff td { padding: 0.4em; }")
    html.append("td.diff_header { background: #f0f0f0; font-weight: bold; }")
    html.append("td.diff_add { background: #e6ffe6; }")
    html.append("td.diff_chg { background: #ffffcc; }")
    html.append("td.diff_sub { background: #ffe6e6; }")
    html.append("</style></head><body>")
    html.append(f"<h2>Test Log - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}</h2>")

    if src_code is not None:
        html.append("<details open>")
        html.append("<summary><strong>Source Code</strong></summary>")
        html.append(f"<pre>{src_code}</pre>")
        html.append("</details>")

    html.append("<ul>")
    for res in results:
        status_class = "pass" if res['status'] == "PASS" else \
                       "tle" if res['status'] == "TLE" else \
                       "mle" if res['status'] == "MLE" else \
                       "noout" if res['status'] == "No .out file" else "fail"
        html.append(f"<li class='{status_class}'><strong>{res['file']}</strong>: {res['status']} — {res['time_ms']:.2f} ms, {res['max_mem_mb']:.2f} MB</li>")
    html.append("</ul>")

    for res in results:
        html.append("<details>")
        html.append(f"<summary><strong>{res['file']}</strong> — Status: {res['status']}</summary><br>")

        if res.get('input') is not None:
            html.append("<details><summary>Input</summary>")
            html.append(f"<pre>{res['input']}</pre>")
            html.append("</details>")

        if res['expected'] is not None:
            html.append("<details><summary>Expected Output</summary>")
            html.append(f"<pre>{res['expected']}</pre>")
            html.append("</details>")

        html.append("<details><summary>Your Output</summary>")
        html.append(f"<pre>{res['output']}</pre>")
        html.append("</details>")

        if res['status'] == "WA":
            differ = difflib.HtmlDiff(wrapcolumn=120)
            diff_html = differ.make_table(
                res['expected'].splitlines(),
                res['output'].splitlines(),
                fromdesc='Expected Output',
                todesc='Your Output',
                context=True,
                numlines=3
            )
            html.append("<details><summary>Diff</summary>")
            html.append(diff_html)
            html.append("</details>")

        html.append("</details>")

    html.append("</body></html>")

    with open(filename, 'w', encoding='utf-8') as f:
        f.write('\n'.join(html))
    print(f"HTML log written to {filename}")

def main():
    parser = argparse.ArgumentParser(description="CP Test Harness")
    parser.add_argument("-e", "--exe", default="./solution", help="Path or command to run the solution (default: ./solution)")
    parser.add_argument("-q", "--quiet", action="store_true", help="Only show failed tests in terminal output")
    parser.add_argument("-l", "--log", action="store_true", help="Save plain text log to '.loghtml/test_log.txt'")
    parser.add_argument("--html", action="store_true", help="Generate pretty HTML diff log in '.loghtml/'")
    parser.add_argument("--test-dir", default=".", help="Directory containing test .in/.out files")
    parser.add_argument("--time-limit", type=float, default=None, help="Per-test time limit in seconds (used with --strict)")
    parser.add_argument("--mem-limit", type=float, default=None, help="Per-test memory limit in MB (used with --strict)")
    parser.add_argument("--strict", action="store_true", help="Strictly kill tests exceeding time/memory limits")
    parser.add_argument("--src-code", default=None, help="Path to source code file to embed once in HTML and log")

    args = parser.parse_args()

    log_dir = ".loghtml"
    os.makedirs(log_dir, exist_ok=True)

    src_code = None
    if args.src_code and os.path.isfile(args.src_code):
        with open(args.src_code, 'r', encoding='utf-8') as f:
            src_code = f.read()

    input_files = sorted(glob.glob(os.path.join(args.test_dir, "*.in")))
    results = []
    log_lines = []
    all_passed = True

    def task(infile):
        res = run_test(infile, args.exe, args.time_limit, args.mem_limit, args.strict)
        try:
            with open(infile, 'r') as f:
                res['input'] = f.read()
        except Exception:
            res['input'] = None
        return res

    with ThreadPoolExecutor() as executor:
        futures = [executor.submit(task, infile) for infile in input_files]
        for f in futures:
            results.append(f.result())

    for res in results:
        status = res['status']
        passed = res['passed']
        if status != "PASS":
            all_passed = False

        if status == "PASS":
            status_str = f"{Fore.GREEN}✅ Passed{Style.RESET_ALL}"
        elif status == "TLE":
            status_str = f"{Fore.YELLOW}⏰ TLE{Style.RESET_ALL}"
        elif status == "MLE":
            status_str = f"{Fore.MAGENTA}🛑 MLE{Style.RESET_ALL}"
        elif status == "No .out file":
            status_str = f"{Fore.CYAN}❔ No Output File{Style.RESET_ALL}"
        else:
            status_str = f"{Fore.RED}❌ WA{Style.RESET_ALL}"

        line = f"{Fore.YELLOW}{res['file']}{Style.RESET_ALL}: Time {res['time_ms']:.2f} ms, Memory {res['max_mem_mb']:.2f} MB, {status_str}"
        if not args.quiet or status != "PASS":
            print(line)
        log_lines.append(strip_ansi(line))

    summary = f"{Fore.GREEN}All tests passed!{Style.RESET_ALL}" if all_passed else f"{Fore.RED}Some tests failed.{Style.RESET_ALL}"
    print(summary)
    log_lines.append(strip_ansi(summary))

    if args.log:
        log_path = os.path.join(log_dir, "test_log.txt")
        with open(log_path, "w") as f:
            f.write('\n'.join(log_lines))
        print(f"Log saved to {log_path}")

    if args.html:
        timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
        html_filename = f"test_log_{timestamp}.html"
        html_path = os.path.join(log_dir, html_filename)
        generate_html(results, html_path, src_code)

        system = platform.system()
        try:
            if system == "Darwin":
                subprocess.run(["open", html_path])
            elif system == "Linux":
                subprocess.run(["xdg-open", html_path])
            elif system == "Windows":
                os.startfile(html_path)
        except Exception as e:
            print(f"Could not open HTML file automatically: {e}")

if __name__ == "__main__":
    main()
