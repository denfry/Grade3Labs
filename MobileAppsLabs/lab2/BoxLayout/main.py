from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.button import Button
from kivy.uix.label import Label
from kivy.uix.textinput import TextInput


class BoxLayoutApp(App):
    def build(self):
        layout = BoxLayout(orientation="vertical",
                           spacing=10,
                           padding=[10, 10, 10, 10])

        layout.add_widget(Label(text="Login",
                                size_hint=(1, None),
                                height=30))

        layout.add_widget(TextInput(size_hint=(1, None),
                                    height=50))

        layout.add_widget(Label(text="Password",
                                size_hint=(1, None),
                                height=30))

        layout.add_widget(TextInput(size_hint=(1, None),
                                    height=50))

        layout.add_widget(Button(text="Login",
                                 size_hint=(1, None),
                                 height=50))

        return layout


if __name__ == "__main__":
    BoxLayoutApp().run()
