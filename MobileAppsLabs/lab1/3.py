from kivy.app import App
from kivy.uix.textinput import TextInput


class MyApp(App):
    def build(self):
        text_input = TextInput(
            size_hint=(None, None),
            size=(400, 200),
            multiline=True,
            pos_hint={'x': .25, 'y': .1}
        )
        return text_input


if __name__ == '__main__':
    MyApp().run()
