import tkinter as tk

def aktualizuj_wspolrzedne(event):
    # Uzupełnij kod tutaj
    pass # Usuń 'pass' i dodaj logikę

root = tk.Tk()
root.title("Śledzenie Myszki")
root.geometry("300x200")

coords_label = tk.Label(root, text="Pozycja: (X: ?, Y: ?)", font=('Arial', 14))
coords_label.pack(pady=50)

# Powiąż zdarzenie  z całym oknem (root)
# def coords(event):
#    coords_label.config(text="Pozycja: (X: {event.x}, Y: {event.y} )")
    

# root.bind("<Motion>", coords)

def aktualizuj_wspolrzedne(event):
    coords_label.config(text=f"Pozycja: (X: {event.x}, Y: {event.y})")

# ... (pozostała część kodu) ...

root.bind('<Motion>', aktualizuj_wspolrzedne) 


root.mainloop()