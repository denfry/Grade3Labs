from kivy.app import App
from kivy.uix.progressbar import ProgressBar


class MyApp(App):
    def build(self):
        Progress = ProgressBar(max=1000)
        Progress.value = 457
        return Progress


if __name__ == '__main__':
    MyApp().run()
