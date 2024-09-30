from kivy.app import App
from kivy.uix.button import Button


def on_press(instance):
    instance.text = "Clicked!"


class MyApp(App):
    def build(self):
        button = Button(
            text="CLICK ME!",
            size_hint=(.5, .5),
            pos_hint={'x': .25, 'y': .1},
            background_color=(1, 0, 0, 1)
        )
        button.bind(on_press=on_press)
        return button


if __name__ == '__main__':
    MyApp().run()
