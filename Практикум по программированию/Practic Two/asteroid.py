'''импорт'''
import pygame
from pygame.transform import scale
from os import path
import time
import math
import settings
import random

pygame.init()
pygame.mixer.init()

screen = pygame.display.set_mode((settings.SCREEN_WIDTH, settings.SCREEN_HEIGHT))

img_dir = path.join(path.dirname(__file__), 'img')
snd_dir = path.join(path.dirname(__file__), 'snd')

''' Импорт изображений астероидов '''
asteroid_images = {}
asteroid_images['medium'] = scale(pygame.image.load(path.join(img_dir, "asteroid.png")).convert_alpha(), (settings.ASTEROID_SIZE_MEDIUM, settings.ASTEROID_SIZE_MEDIUM))
asteroid_images['big'] = scale(pygame.image.load(path.join(img_dir, "big.png")).convert_alpha(), (settings.ASTEROID_SIZE_BIG, settings.ASTEROID_SIZE_BIG))

''' Класс астероидов '''
class Asteroid(pygame.sprite.Sprite):
    ''' Инициализация параметров скорости, появления и движения астероидов '''
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.spin = random.choice(['l', 'r'])
        self.type = random.choice(['medium', 'big'])
        self.image = asteroid_images[self.type]
        self.rect = self.image.get_rect()
        self.rot_image = self.image.copy()
        self.rect.x = random.randrange(settings.SCREEN_WIDTH - self.rect.width)
        self.rect.y = random.randrange(-100, -40)
        self.speedy = random.randrange(settings.MIN_ASTEROID_SPEED_Y, settings.MAX_ASTEROID_SPEED_Y)
        self.speedx = random.randrange(-settings.ASTEROID_SPEED_X, settings.ASTEROID_SPEED_X)
        self.rot = 0

    ''' Создание новых и управление старыми астероидами, проверка на нахождение их в игровом поле '''
    def update(self):
        self.rect.x += self.speedx
        self.rect.y += self.speedy
        if self.rect.top > settings.SCREEN_HEIGHT + 30 or self.rect.left < -85 or self.rect.right > settings.SCREEN_WIDTH + 85:
            self.rect.x = random.randrange(settings.SCREEN_WIDTH - self.rect.width)
            self.rect.y = random.randrange(-130, -90)
            self.speedy = random.randrange(settings.MIN_ASTEROID_SPEED_Y, settings.MAX_ASTEROID_SPEED_Y)

        if self.spin == 'l':
            self.rot += 1
        else:
            self.rot -= 1
            
        self.image = pygame.transform.rotozoom(self.rot_image, self.rot, 1)
        self.rect = self.image.get_rect(center = self.rect.center)
        screen.blit(self.image, self.rect)