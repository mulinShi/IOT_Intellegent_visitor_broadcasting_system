import base64
import os
import requests
import pygame


def main():
    url = "http://loadbalancer-1062814621.us-east-2.elb.amazonaws.com/Recognize"
    with open("2.jpg","rb") as f:
        img = base64.b64encode(f.read()).decode()
    data = {"img" : img}
    response = requests.post(url,data=data)
    print(response.text)
    if response.text != "":
        with open("output.mp3", "wb") as f:
            f.write(base64.b64decode(response.text))
        
        pygame.mixer.init()
        pygame.mixer.music.load("output.mp3")
        pygame.mixer.music.play()
        while pygame.mixer.music.get_busy() == True:
            continue
  
if __name__ == "__main__":
    main()