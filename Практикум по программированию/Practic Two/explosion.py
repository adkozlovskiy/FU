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

''' Все вариации взрыва '''
explosion_anim = {}
explosion_anim['lg'] = []
explosion_anim['sm'] = []
explosion_anim['player'] = []

''' Импорт изображений взрыва '''
for i in range(9):
    filename = 'regularExplosion0{}.png'.format(i)
    img = pygame.image.load(path.join(img_dir, filename)).convert_alpha()
    img_lg = pygame.transform.scale(img, (150, 150))
    explosion_anim['lg'].append(img_lg)
    img_sm = pygame.transform.scale(img, (75, 75))
    explosion_anim['sm'].append(img_sm)
    filename = 'sonicExplosion0{}.png'.format(i)
    img = pygame.image.load(path.join(img_dir, filename)).convert_alpha()
    explosion_anim['player'].append(img)

''' Класс взрыва '''
class Explosion(pygame.sprite.Sprite):
    ''' Создание точки и размера взрыва под соответствующее изображение '''
    def __init__(self, center, size):
        pygame.sprite.Sprite.__init__(self)
        self.size = size
        self.image = explosion_anim[self.size][0]
        self.rect = self.image.get_rect()
        self.rect.center = center
        self.frame = 0
        self.last_update = pygame.time.get_ticks()
        self.frame_rate = settings.FPS

    ''' Обновление взрыва в кадре '''
    def update(self):
        now = pygame.time.get_ticks()
        if now - self.last_update > self.frame_rate:
            self.last_update = now
            self.frame += 1
            if self.frame == len(explosion_anim[self.size]):
                self.kill()
            else:
                center = self.rect.center
                self.image = explosion_anim[self.size][self.frame]
                self.rect = self.image.get_rect()
                self.rect.center = center