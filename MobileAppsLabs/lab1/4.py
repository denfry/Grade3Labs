from kivy.app import App
from kivy.uix.checkbox import CheckBox


class MyApp(App):
    def build(self):
        def on_checkbox_active(checkbox_parametr, value):
            if value:
                print("The Checkbox", checkbox_parametr, "is active")
            else:
                print("The Checkbox", checkbox_parametr, "is inactive")

        checkbox = CheckBox()
        checkbox.bind(active=on_checkbox_active)
        return checkbox


if __name__ == '__main__':
    MyApp().run()
