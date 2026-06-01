import tkinter as tk
import json

root = tk.Tk()

root.title("Lista zadan")
root.geometry("300x700")

row = 1
tasks = []

def save_tasks():
    with open("data.json", "w") as f:
        json.dump(
            [{"name": task["name"], "checked": task["checked"]} for task in tasks],
            f
        )

def load_tasks():
    global row

    try:
        with open("data.json", "r") as f:
            loaded = json.load(f)

        for task in loaded:
            add_task(task["name"], task["checked"])

    except:
        pass


def add_task(value, checked=False):
    global row

    current_row = row

    label_2 = tk.Label(root, text=value)
    label_2.grid(row=current_row, column=0)

    checkbox_var = tk.IntVar(value=1 if checked else 0)

    task = {
        "name": value,
        "checked": checkbox_var.get()
    }

    checkbox_1 = tk.Checkbutton(root, variable=checkbox_var)
    checkbox_1.grid(row=current_row, column=1)

    def update():
        task["checked"] = checkbox_var.get()
        save_tasks()

    checkbox_1.config(command=update)

    def delete_task():
        label_2.destroy()
        checkbox_1.destroy()
        button_delete.destroy()

        tasks.remove(task)
        save_tasks()

    button_delete = tk.Button(root, text="Usun", command=delete_task)
    button_delete.grid(row=current_row, column=2)

    task["label"] = label_2
    task["checkbox"] = checkbox_1

    tasks.append(task)

    row += 1



def open_window():
    new_window = tk.Toplevel(root)
    new_window.title("Nowe okno")
    new_window.geometry("200x200")

    time = 0
    running = False

    label_3 = tk.Label(new_window, text="Timer")
    label_timer = tk.Label(new_window, text=str(time), font=("Arial", 20))

    def update_timer():
        nonlocal time, running

        if running:
            label_timer.config(text=str(time))
            time += 1
            new_window.after(1000, update_timer)

    def start_timer():
        nonlocal running

        if not running:
            running = True
            update_timer()

    def stop_timer():
        nonlocal running
        running = False

    button_3 = tk.Button(new_window, text="Start", command=start_timer)
    button_4 = tk.Button(new_window, text="Stop", command=stop_timer)

    label_3.grid(row=1, column=0)
    label_timer.grid(row=2, column=0)
    button_3.grid(row=3, column=0)
    button_4.grid(row=3, column=1)

def action_1():
    value = entry_1.get()

    if value == "":
        return

    add_task(value)

    save_tasks()

    entry_1.delete(0, 'end')


label_1 = tk.Label(root, text="Zadanie:")
entry_1 = tk.Entry(root)

button = tk.Button(root, text="Dodaj zadanie", command=action_1)
button_2 = tk.Button(root, text="Timer", command=open_window)

label_1.grid(row=0, column=0)
entry_1.grid(row=0, column=1, padx=10)

button.grid(row=0, column=3, padx=10)
button_2.grid(row=1, column=3, padx=10)

load_tasks()

root.mainloop()