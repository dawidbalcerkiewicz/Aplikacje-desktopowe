import tkinter as tk

def na_najechaniu(event):
    # Uzupełnij logikę dla najechania
    pass

def na_kliknieciu(event):
    # Uzupełnij logikę dla kliknięcia
    pass

def na_opuszczeniu(event):
    # Uzupełnij logikę dla opuszczenia
    pass

root = tk.Tk()
root.title("Panel Alarmowy")
root.geometry("350x150")

status_panel = tk.Label(
    root, 
    text="SYSTEM ROZBROJONY", 
    bg="green", 
    fg="white", 
    font=('Arial', 16, 'bold'),
    width=25,
    height=3
)
status_panel.pack(padx=20, pady=20)


# Powiązanie zdarzeń
status_panel.bind("<Enter>", lambda event: event.widget.config(bg="yellow", text="UZBROJENIE MOZLIWE"))
status_panel.bind("<Button-1>", lambda event: event.widget.config(bg="red", text="SYSTEM UZBROJONY"))
status_panel.bind("<Leave>", lambda event: event.widget.config(bg="green", text="SYSTEM ROZBROJONY"))
# status_panel.bind(...) - dodaj tutaj 3 powiązania bind

root.mainloop()