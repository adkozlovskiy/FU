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

''' Импорт изображения ракеты '''
laser_image_temp = pygame.image.load(path.join(img_dir, 'greenLaser.png')).convert_alpha()
laser_image = pygame.transform.scale(laser_image_temp, (5, 40))

''' Класс рокеты '''
class Rocket(pygame.sprite.Sprite):
    ''' Скорость, угол (зависит от места нахождения корабля) '''
    def __init__(self, x, y, angle):
        pygame.sprite.Sprite.__init__(self)
        self.image = laser_image
        self.rot_image = self.image.copy()
        self.angle = angle
        self.rect = self.image.get_rect()
        self.rect.bottom = y
        self.rect.centerx = x
        self.speedy = -settings.ROCKET_SPEED
        self.dx = 0
        self.dy = 0
        self.speed = 3
        self.lifetime = settings.ROCKET_LIFETIME


    def update(self):
        self.lifetime -= 1

        angle = self.angle * math.pi / 180
        self.dx += self.speed * - math.sin(angle)
        self.dy += self.speed * - math.cos(angle)
            
        self.image = pygame.transform.rotozoom(self.rot_image, self.angle, 1)
        self.rect = self.image.get_rect(center = self.rect.center)
        screen.blit(self.image, self.rect)

        self.rect.x += self.dx
        self.rect.y += self.dy

        ''' Уничтожение ракеты, которая вышла за пределами игрового поля '''
        if self.rect.bottom < 0:
            self.kill()

        ''' Уничтожение ракеты по истечению время ее жизни '''
        if self.lifetime == 0:
            self.kill()