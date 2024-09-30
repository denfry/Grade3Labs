from kivy.app import App
from kivy.uix.label import Label


class MyApp(App):
    def build(self):
        label = Label(
            text="[color=#00ff00]Hello, World![/color]",
            markup=True,
            size_hint=(.5, .5),
            pos_hint={'x': .25, 'y': .1}
        )
        return label


if __name__ == '__main__':
    MyApp().run()
