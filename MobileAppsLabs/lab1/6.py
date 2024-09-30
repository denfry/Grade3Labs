from kivy.app import App
from kivy.uix.image import Image


class MyApp(App):
    def build(self):
        img = Image(source="images/image.jpg")
        return img


if __name__ == '__main__':
    MyApp().run()
