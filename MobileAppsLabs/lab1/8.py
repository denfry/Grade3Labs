from kivy.app import App
from kivy.uix.togglebutton import ToggleButton


class MyApp(App):
    def build(self):
        b = ToggleButton(text="python",
                         border=(26, 26, 26, 26),
                         font_size=200)
        return b


if __name__ == '__main__':
    MyApp().run()
