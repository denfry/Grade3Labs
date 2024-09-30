from kivy.app import App
from kivy.uix.screenmanager import ScreenManager, Screen
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.button import Button
from kivy.uix.label import Label
from kivy.uix.textinput import TextInput


class LoginScreen(Screen):
    def __init__(self, **kwargs):
        super(LoginScreen, self).__init__(**kwargs)
        layout = BoxLayout(orientation="vertical", spacing=10, padding=[10, 10, 10, 10])

        layout.add_widget(Label(text="Login", size_hint=(1, None), height=30))

        layout.add_widget(TextInput(size_hint=(1, None), height=50))

        layout.add_widget(Label(text="Password", size_hint=(1, None), height=30))

        layout.add_widget(TextInput(password=True, size_hint=(1, None), height=50))

        login_button = Button(text="Login", size_hint=(1, None), height=50)
        login_button.bind(on_press=self.switch_to_welcome)
        layout.add_widget(login_button)

        self.add_widget(layout)

    def switch_to_welcome(self, instance):
        self.manager.current = 'welcome'


class WelcomeScreen(Screen):
    def __init__(self, **kwargs):
        super(WelcomeScreen, self).__init__(**kwargs)
        layout = BoxLayout(orientation="vertical", spacing=10, padding=[10, 10, 10, 10])

        layout.add_widget(Label(text="Welcome!", size_hint=(1, None), height=50))

        logout_button = Button(text="Logout", size_hint=(1, None), height=50)
        logout_button.bind(on_press=self.switch_to_login)
        layout.add_widget(logout_button)

        self.add_widget(layout)

    def switch_to_login(self, instance):
        self.manager.current = 'login'


class MyApp(App):
    def build(self):
        sm = ScreenManager()

        sm.add_widget(LoginScreen(name='login'))
        sm.add_widget(WelcomeScreen(name='welcome'))

        return sm


if __name__ == "__main__":
    MyApp().run()
