from kivy.app import App
from kivy.uix.floatlayout import FloatLayout
from kivy.uix.button import Button
from kivy.uix.label import Label
from kivy.uix.textinput import TextInput


class FloatLayoutApp(App):
    def build(self):
        layout = FloatLayout()

        layout.add_widget(Label(text="Login",
                                size_hint=(None, None),
                                size=(100, 30),
                                pos_hint={'center_x': 0.5, 'center_y': 0.7}))

        layout.add_widget(TextInput(size_hint=(None, None),
                                    size=(200, 50),
                                    pos_hint={'center_x': 0.5, 'center_y': 0.6}))

        layout.add_widget(Label(text="Password",
                                size_hint=(None, None),
                                size=(100, 30),
                                pos_hint={'center_x': 0.5, 'center_y': 0.45}))

        layout.add_widget(TextInput(size_hint=(None, None),
                                    size=(200, 50),
                                    pos_hint={'center_x': 0.5, 'center_y': 0.35}))

        layout.add_widget(Button(text="Login",
                                 size_hint=(None, None),
                                 size=(100, 50),
                                 pos_hint={'center_x': 0.5, 'center_y': 0.2}))

        return layout


if __name__ == "__main__":
    FloatLayoutApp().run()
