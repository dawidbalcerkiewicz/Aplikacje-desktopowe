import tkinter as tk
from tkinter import ttk

def show_selection(event):
    """Funkcja obsługująca zdarzenie wyboru w Combobox."""
    print(f"Wybrano opcję: {combo.get()}")

def start_progress():
    """Funkcja symulująca i sterująca paskiem postępu."""
    # Uruchomienie paska w trybie indeterministycznym
    pbar.start(10) # 10 to interwał w milisekundach
    status_label.config(text="Status: Proces trwa...")

def stop_progress():
    """Funkcja zatrzymująca pasek postępu."""
    pbar.stop()
    status_label.config(text="Status: Proces zakończony.")

root = tk.Tk()
root.title("Zaawansowane GUI Ttk")

# --- 1. Notebook (Karty) ---
notebook = ttk.Notebook(root)
notebook.pack(pady=10, padx=10, expand=True, fill="both")

# Tworzenie ramek na karty
tab1 = ttk.Frame(notebook, padding="10")
tab2 = ttk.Frame(notebook, padding="10")

# Dodawanie kart do Notebook
notebook.add(tab1, text="Wyglad")
notebook.add(tab2, text="Prywatnosc")

# --- Zawartość Karty 1 (Combobox) ---
ttk.Label(tab1, text="Wybierz motyw:").grid(row=0, column=0, padx=5, pady=5, sticky="w")

programming_languages = ["Jasny", "Ciemny"]
combo = ttk.Combobox(tab1, values=programming_languages, state="readonly")
combo.current(0) # Ustawienie domyślnej wartości
combo.grid(row=0, column=1, padx=5, pady=5, sticky="ew")

# Przypisanie zdarzenia <<ComboboxSelected>>
combo.bind("<<ComboboxSelected>>", show_selection)

ttk.Label(tab1, text="Kontrast:").grid(row=1, column=0, padx=5, pady=5, sticky="w")
check = ttk.Checkbutton(tab1).grid(row=1, column=1, padx=0, pady=0, sticky="w")

# --- Zawartość Karty 2 (Progressbar) ---
status_label = ttk.Label(tab2, text="Prywatnosc")
status_label.pack(pady=10)

status_label1 = ttk.Label(tab2, text="Wyszstkie").grid(row=0, column=0, padx=0, pady=0, sticky="w")
status_label2 = ttk.Label(tab2, text="Anonimowe").grid(row=1, column=0, padx=0, pady=0, sticky="w")
status_label3 = ttk.Label(tab2, text="Zadne").grid(row=2, column=0, padx=0, pady=0, sticky="w")

radio1 = ttk.Radiobutton(tab2).grid(row=0, column=1, padx=0, pady=0, sticky="w")
radio2 = ttk.Radiobutton(tab2).grid(row=1, column=1, padx=0, pady=0, sticky="w")
radio3 = ttk.Radiobutton(tab2).grid(row=2, column=1, padx=0, pady=0, sticky="w")


#pbar = ttk.Progressbar(tab2, orient="horizontal", length=300, mode="indeterminate")
#pbar.pack(pady=10)

#ttk.Button(tab2, text="Start Procesu", command=start_progress).pack(side="left", padx=5)
#ttk.Button(tab2, text="Stop Procesu", command=stop_progress).pack(side="left", padx=5)

root.mainloop()