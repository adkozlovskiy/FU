import pygame
import settings

pygame.init()
font_name = pygame.font.match_font('arial')

''' Отрисовка жизней и их оставшегося количества, указание по координатом места отрисовки '''
def draw_lives(surf, x, y, lives, img):
    for i in range(lives):
        img_rect = img.get_rect()
        img_rect.x = x - 75 * i
        img_rect.y = y
        surf.blit(img, img_rect)

''' Текст перед и после игры '''
def draw_text(surf, text, size, x, y):
    font = pygame.font.Font(font_name, size)
    text_surface = font.render(text, True, settings.COLOR_WHITE)
    text_rect = text_surface.get_rect()
    text_rect.midtop = (x, y)
    surf.blit(text_surface, text_rect)