import base64
import os
import RPi.GPIO as GPIO
import requests
import pygame

BUTTON = 23
SERVER_IP = "loadbalancer-1062814621.us-east-2.elb.amazonaws.com"

def setup():
    GPIO.setmode(GPIO.BCM)
    GPIO.setwarnings(False)
    GPIO.setup(BUTTON, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

def callback(channel):
    os.system("wget http://127.0.0.1:8080/?action=snapshot -O output.jpg")
    url = "http://"+ SERVER_IP +"/Recognize"
    with open("output.jpg","rb") as f:
        img = base64.b64encode(f.read()).decode()
    data = {"img" : img}
    response = requests.post(url,data=data)
    if response.text != "null":
        # print(response.text)
        with open("output.mp3", "wb") as f:
            f.write(base64.b64decode(response.text))
        
        pygame.mixer.init()
        pygame.mixer.music.load("output.mp3")
        pygame.mixer.music.play()
        while pygame.mixer.music.get_busy() == True:
            continue

if __name__ == "__main__":
    setup()
    GPIO.add_event_detect(BUTTON, GPIO.RISING, callback=callback, bouncetime=200)
    # callback()