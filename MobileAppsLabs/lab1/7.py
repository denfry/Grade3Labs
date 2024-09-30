from kivy.app import App
from kivy.uix.switch import Switch


class MyApp(App):
    def build(self):
        s = Switch(active=True)
        return s


if __name__ == '__main__':
    MyApp().run()
