from tkinter import *

root = Tk()
c = Canvas(root, width=600, height=600, bg="white")
c.pack()

center_x = 300
center_y = 300

Radius = 200
radius = 10

speed = 10
dim = 1

ball = c.create_oval(center_x - Radius, center_y - Radius, center_x + Radius, center_y + Radius, fill = "white")

point = c.create_oval(center_x - radius, center_y - radius, center_x + radius, center_y + radius, fill = "red")

def motion():
    pass
    # Я не знаю, что тут делать

motion()

root.mainloop()
