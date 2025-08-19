import requests
from bs4 import BeautifulSoup
import random
import json
import os

BASE_URL = "https://cses.fi"
PROBLEMSET_URL = f"{BASE_URL}/problemset/"
STATUS_FILE = "cses_status.json"

def fetch_problems():
    response = requests.get(PROBLEMSET_URL)
    soup = BeautifulSoup(response.text, "html.parser")
    
    problems = {}
    for li in soup.select("ul.task-list li.task a"):
        title = li.text.strip()
        link = BASE_URL + li["href"]
        pid = int(li["href"].split("/")[-1])
        problems[pid] = {"title": title, "link": link}
    return problems

def load_status():
    if os.path.exists(STATUS_FILE):
        with open(STATUS_FILE, "r") as f:
            return json.load(f)
    return {}

def save_status(status):
    with open(STATUS_FILE, "w") as f:
        json.dump(status, f, indent=2)

def main():
    problems = fetch_problems()
    print(f"✅ Loaded {len(problems)} problems from CSES.\n")

    status = load_status()
    
    # initialize new problems as 'pending'
    for pid in problems:
        if str(pid) not in status:
            status[str(pid)] = "pending"
    save_status(status)

    print("Welcome to the CSES Randomizer!")
    print("Type 'done' to mark problem done, 'pending' to keep it pending, 'exit' to quit.\n")

    while True:
        remaining = [pid for pid, flag in status.items() if flag != "done"]
        if not remaining:
            print("🎉 All problems marked done! Congrats!")
            break

        pid = int(random.choice(remaining))
        title = problems[pid]["title"]
        link = problems[pid]["link"]

        # If first time seeing this problem
        if status[str(pid)] == "pending":
            print(f"⏳ Next problem (pending): {title}")
        else:
            print(f"🆕 Next problem: {title}")

        print(f"🔗 {link}")
        action = input("Mark as done / pending / exit (d/p/e)? ").strip().lower()
        if action == "e" or action == "exit":
            print("👋 Exiting. Your progress is saved!")
            break
        elif action == "d" or action == "done":
            status[str(pid)] = "done"
        elif action == "p" or action == "pending":
            status[str(pid)] = "pending"
        else:
            print("⚠️ Invalid input. Skipping this problem.")

        save_status(status)
        print(f"✅ Progress saved. {len([f for f in status.values() if f=='done'])}/{len(problems)} done.\n")

if __name__ == "__main__":
    main()
