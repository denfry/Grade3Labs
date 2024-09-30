from kivy.app import App
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager, Screen
from kivy.uix.popup import Popup
from kivy.uix.label import Label


class LoginScreen(Screen):
    def verify_credentials(self):
        """Verify the user credentials."""
        username = self.ids.username_input.text
        password = self.ids.password_input.text

        if self.check_credentials(username, password):
            self.manager.get_screen('welcome').ids.welcome_label.text = f"Добро пожаловать, {username}!"
            self.manager.current = 'welcome'
        else:
            self.show_popup("Ошибка!", "Неправильное имя пользователя или пароль!")

    def check_credentials(self, username, password):
        """Read the users.txt file and compare credentials."""
        try:
            with open('users.txt', 'r') as f:
                for line in f.readlines():
                    file_username, file_password = line.strip().split(' ')
                    if file_username == username and file_password == password:
                        return True
        except FileNotFoundError:
            self.show_popup("Ошибка!", "Файл users.txt не найден!")
        return False

    def clear_inputs(self):
        self.ids.username_input.text = ""
        self.ids.password_input.text = ""
        self.ids.status.text = ""

    @staticmethod
    def show_popup(title, message):
        popup = Popup(title=title, content=Label(text=message), size_hint=(0.6, 0.4))
        popup.open()

    def toggle_password_visibility(self):
        self.ids.password_input.password = not self.ids.password_input.password

        if self.ids.password_input.password:
            self.ids.toggle_password_button.text = "Показать пароль"
        else:
            self.ids.toggle_password_button.text = "Скрыть пароль"


class RegisterScreen(Screen):
    def register_user(self):
        username = self.ids.new_username.text
        password = self.ids.new_password.text

        if username and password:
            if self.add_new_user(username, password):
                self.show_popup("Успешно!", f"Пользователь {username} успешно зарегистрирован!")
                self.clear_inputs()
                self.manager.current = 'login'
            else:
                self.show_popup("Ошибка!", "Пользователь с таким именем уже существует!")
        else:
            self.show_popup("Ошибка!", "Поля не могут быть пустыми!")

    @staticmethod
    def add_new_user(username, password):
        """Add a new user to the users.txt file."""
        try:
            with open('users.txt', 'r') as f:
                users = [line.strip().split(' ')[0] for line in f.readlines()]
                if username in users:
                    return False
        except FileNotFoundError:
            pass

        with open('users.txt', 'a') as f:
            f.write(f"{username} {password}\n")
        return True

    def clear_inputs(self):
        self.ids.new_username.text = ""
        self.ids.new_password.text = ""

    @staticmethod
    def show_popup(title, message):
        popup = Popup(title=title, content=Label(text=message), size_hint=(0.6, 0.4))
        popup.open()


class WelcomeScreen(Screen):
    def logout(self):
        self.manager.get_screen('login').clear_inputs()
        self.manager.current = 'login'


class MyApp(App):
    def build(self):
        Builder.load_file('login.kv')
        sm = ScreenManager()
        sm.add_widget(LoginScreen(name='login'))
        sm.add_widget(RegisterScreen(name='register'))
        sm.add_widget(WelcomeScreen(name='welcome'))
        return sm


if __name__ == "__main__":
    MyApp().run()
