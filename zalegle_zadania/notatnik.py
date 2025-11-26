import tkinter as tk
from tkinter import ttk
 
bufor_notatki = ""
 
def ustaw_tryb():
    """Zmienia tryb edycji Text w zależności od wybranego przycisku radiowego."""
    if tryb_var.get() == "normalny":
        text.config(state=tk.NORMAL)
    else:
        text.config(state=tk.DISABLED)
 
def obsluga_buforowania():
    """Buforuje i ukrywa lub przywraca notatkę w zależności od stanu Checkbutton."""
    global bufor_notatki
    if bufor_var.get() == 1: 
        bufor_notatki = text.get("1.0", tk.END).strip()
        text.config(state=tk.NORMAL)
        text.delete("1.0", tk.END)
        text.insert(tk.END, "Treść została bezpiecznie zbuforowana i ukryta.")
        text.config(state=tk.DISABLED)
    else:  
        text.config(state=tk.NORMAL)
        text.delete("1.0", tk.END)
        text.insert(tk.END, bufor_notatki)
 
 
root = tk.Tk()
root.title("Notatnik z buforowaniem")
 
 
text = tk.Text(root, width=30, height=15, wrap="word")
text.pack(padx=10, pady=10)
 
 
text.insert(tk.END, "")
 
# 
frame_dol = ttk.Frame(root)
frame_dol.pack(padx=10, pady=10)
 
 
tryb_var = tk.StringVar(value="normalny")
 
# Radiobutton
rb1 = ttk.Radiobutton(frame_dol, text="Tryb normalny", variable=tryb_var, value="normalny", command=ustaw_tryb)
rb2 = ttk.Radiobutton(frame_dol, text="Tylko do odczytu", variable=tryb_var, value="tylko_odczyt", command=ustaw_tryb)
rb1.grid(row=0, column=0, padx=5)
rb2.grid(row=0, column=1, padx=5)
 
# Checkbutton 
bufor_var = tk.IntVar(value=0)
chk_bufor = ttk.Checkbutton(frame_dol, text="Buforuj i ukryj notatkę", variable=bufor_var, command=obsluga_buforowania)
chk_bufor.grid(row=1, column=0, columnspan=2, pady=5)
 
root.mainloop()