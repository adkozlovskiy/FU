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

''' Импорт изображений бонусов '''
powerup_images = {}
shield = pygame.image.load(path.join(img_dir, 'heal.png')).convert_alpha()
powerup_images['shield'] = pygame.transform.scale(shield, (40, 40))
temp_image_bomb = pygame.image.load(path.join(img_dir, 'bomb_activate.png')).convert_alpha()
powerup_images['bomb'] = pygame.transform.scale(temp_image_bomb, (40, 40))

bonus_5_red = pygame.image.load(path.join(img_dir, '5r.png')).convert_alpha()

powerup_images['5r'] = pygame.transform.scale(bonus_5_red, (40, 40))

''' Класс бонуса '''
class Bonus(pygame.sprite.Sprite):
    ''' Инициализация параметров '''
    def __init__(self, center):
        pygame.sprite.Sprite.__init__(self)
        self.type = random.choice(['shield', 'bomb', '5r', '5r'])
        self.image = powerup_images[self.type]
        self.rect = self.image.get_rect()
        self.rect.center = center
        self.speedy = 2

    ''' Обновление и проверка на выход за игровое поле '''
    def update(self):
        self.rect.y += self.speedy
        if self.rect.top > settings.SCREEN_HEIGHT:
            self.kill()