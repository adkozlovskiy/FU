''' Импорты '''
import pygame
import random
from pygame.transform import scale
from os import path
import time
import math
import settings
import functions
import asteroid
import rocket
import bonus
import explosion

pygame.init()
pygame.mixer.init()

screen = pygame.display.set_mode((settings.SCREEN_WIDTH, settings.SCREEN_HEIGHT))
pygame.display.set_caption("Star Wars: The Strike Back")
clock = pygame.time.Clock()

img_dir = path.join(path.dirname(__file__), 'img')
snd_dir = path.join(path.dirname(__file__), 'snd')

''' Импорт изображений '''
asteroid_images = {}
asteroid_images['medium'] = scale(pygame.image.load(path.join(img_dir, "asteroid.png")).convert_alpha(), (settings.ASTEROID_SIZE_MEDIUM, settings.ASTEROID_SIZE_MEDIUM))
asteroid_images['big'] = scale(pygame.image.load(path.join(img_dir, "big.png")).convert_alpha(), (settings.ASTEROID_SIZE_BIG, settings.ASTEROID_SIZE_BIG))

''' Главный класс '''
class Player(pygame.sprite.Sprite):

    ''' Инициализация переменных корабля: скорость, угол, точка появления '''
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        self.speed = 0
        self.angle = 0
        self.radius = settings.COLISION_RADIUS
        self.image = player_image
        self.rot_image = self.image.copy()

        self.rect = self.image.get_rect()
        self.rect.centerx = settings.SCREEN_WIDTH / 2
        self.rect.bottom = settings.SCREEN_HEIGHT / 2 + 400

        self.hidden = False
        self.flying = False
        self.invisible = True
        self.invisible_time = 120

        self.angle_speed = settings.PLAYER_ANGLE_SPEED
        self.lives = settings.PLAYER_HP

        self.hide_timer = pygame.time.get_ticks()
        self.dx = 0
        self.dy = 0

    ''' Перенос за пределы при взрыве корабля '''
    def hide(self):
        self.hidden = True
        self.hide_timer = pygame.time.get_ticks()
        self.rect.center = (0, -400)

    ''' Движение корабля, уязвимость(при отсутствии невидимости, точка появления, поворот корабля) '''
    def update(self):
        self.flying = False
        if self.invisible:
            self.image = invisible_player
            self.rot_image = self.image.copy()
            self.invisible_time -= 1
        
        if self.invisible_time == 0:
            self.invisible = False

        if self.hidden and pygame.time.get_ticks() - self.hide_timer > 3000:
            self.rect.centerx = settings.SCREEN_WIDTH / 2
            self.rect.bottom = settings.SCREEN_HEIGHT / 2 + 400
            self.hidden = False
            self.invisible = True
            self.invisible_time = 120

        self.dx = 0
        self.dy = 0

        ''' Обработка нажатий клавиш поворота '''
        keystate = pygame.key.get_pressed()
        if keystate[pygame.K_LEFT]:
            if not self.hidden:
                if self.angle == 360:
                    self.angle = 0

                self.angle += self.angle_speed

        if keystate[pygame.K_RIGHT]:
            if not self.hidden:
                if self.angle == 0:
                    self.angle = 360

                self.angle -= self.angle_speed

        ''' Плавное торможение '''
        if self.speed > 0:
            self.speed -= 0.5
           
        ''' Обработка клавиши ускорения '''
        if keystate[pygame.K_UP]:
            if not self.hidden:
                self.speed = settings.PLAYER_SPEED
                self.flying = True

        if not self.invisible:
            if self.flying:
                self.image = player_image_flying    
                self.rot_image = self.image.copy()
            else:
                self.image = player_image
                self.rot_image = self.image.copy()

        ''' Изменение угла '''
        if not self.hidden:
            angle = self.angle * math.pi / 180            
            self.image = pygame.transform.rotozoom(self.rot_image, self.angle, 1)
            self.rect = self.image.get_rect(center = self.rect.center)
            screen.blit(self.image, self.rect)

            self.dx += self.speed * - math.sin(angle)
            self.dy += self.speed * - math.cos(angle)
            
            ''' Изменение положения rect с учетом угла '''
            self.rect.x += self.dx
            self.rect.y += self.dy
           
           
        ''' Контроль выхода за пределы экрана '''
        if self.rect.right > settings.SCREEN_WIDTH + 20:
            self.rect.left = 0
        if self.rect.left < -25:
            self.rect.right = settings.SCREEN_WIDTH

        if self.rect.top > settings.SCREEN_HEIGHT:
            self.rect.bottom = 0
        if self.rect.bottom < 0:
            self.rect.top = settings.SCREEN_HEIGHT

    ''' Стрельба '''
    def shoot(self):
        shoot_sound.play()
        r = rocket.Rocket(self.rect.centerx, self.rect.centery, self.angle)
        rockets.add(r)
        all_sprites.add(r)

''' Импорт всех изображений и звуков'''
background = pygame.image.load(path.join(img_dir, 'background.jpg')).convert()
background_rect = background.get_rect()

logo = pygame.image.load(path.join(img_dir, 'logo.png')).convert_alpha()
logo_rect = logo.get_rect()

gameover = pygame.image.load(path.join(img_dir, 'gameover.png')).convert_alpha()
gameover_rect = gameover.get_rect()

shoot_sound = pygame.mixer.Sound(path.join(snd_dir, 'pew2.wav'))
expl_sound = pygame.mixer.Sound(path.join(snd_dir, 'expl.wav'))
expl_player_sound = pygame.mixer.Sound(path.join(snd_dir, 'expl_player.wav'))
heal_sound = pygame.mixer.Sound(path.join(snd_dir, 'heal.wav'))
repair_sound = pygame.mixer.Sound(path.join(snd_dir, 'repair.wav'))
start_sound = pygame.mixer.Sound(path.join(snd_dir, 'start.wav'))
died_sound = pygame.mixer.Sound(path.join(snd_dir, 'dead.wav'))
r5_sound = pygame.mixer.Sound(path.join(snd_dir, '5r.wav'))

pygame.mixer.music.load(path.join(snd_dir, 'music2.mp3'))
pygame.mixer.music.set_volume(settings.MUSIC_VOLUME)

player_img = pygame.image.load(path.join(img_dir, "heal.png")).convert_alpha()
player_mini_img = pygame.transform.scale(player_img, (55, 55))

player_image = pygame.image.load(path.join(img_dir, "stay.png")).convert_alpha()
player_image = pygame.transform.scale(player_image, (110, 110))

player_image_flying = pygame.image.load(path.join(img_dir, "fly0.png")).convert_alpha()
player_image_flying = pygame.transform.scale(player_image_flying, (110, 110))

invisible_player_temp = pygame.image.load(path.join(img_dir, 'invisible.png')).convert_alpha()
invisible_player = pygame.transform.scale(invisible_player_temp, (110, 110))
laser_image_temp = pygame.image.load(path.join(img_dir, 'greenLaser.png')).convert_alpha()
laser_image = pygame.transform.scale(laser_image_temp, (5, 40))

pygame.mixer.music.play(loops=-1)
first_time_flag = True
game_over = True
running = True

''' Создание астероида '''
def create_asteroid():
    a = asteroid.Asteroid()
    all_sprites.add(a)
    asteroids.add(a)

''' Начальное окно с изначальнам изображением и возможностью начать игру '''
def show_go_screen():
    waiting = True
    while waiting:
        clock.tick(settings.FPS)
        global asteroids
        global first_time_flag
        screen.blit(background, background_rect)
        asteroids.update()
        asteroids.draw(screen)

        if first_time_flag:
            screen.blit(logo, logo_rect)
        else:
            functions.draw_text(screen, str(score), 28, settings.SCREEN_WIDTH / 2, 10)
            screen.blit(gameover, gameover_rect)

        pygame.display.flip()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
            if event.type == pygame.MOUSEBUTTONDOWN:
                pressed = pygame.mouse.get_pressed()
                if pressed[0]:

                    if first_time_flag == True:
                        start_sound.play()
                        first_time_flag = False

                    waiting = False
                    game_over = False

''' Цикл игры '''
while running:
    if game_over:
        all_sprites = pygame.sprite.Group()
        asteroids = pygame.sprite.Group()
        player = Player()
        all_sprites.add(player)
        rockets = pygame.sprite.Group()
        powerups = pygame.sprite.Group()
        
        for i in range(settings.ASTEROIDS_COUNT):
            create_asteroid()

        show_go_screen()
        score = 0
        game_over = False
        
    clock.tick(settings.FPS)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                if not player.hidden:
                    player.shoot()
    
    hits = pygame.sprite.groupcollide(asteroids, rockets, True, True)
    for hit in hits:
        expl_sound.play()
        score += 1
        expl = explosion.Explosion(hit.rect.center, 'sm')
        all_sprites.add(expl)

        if random.random() > settings.POW_CHANCE:
            b = bonus.Bonus(hit.rect.center)
            all_sprites.add(b)
            powerups.add(b)

        create_asteroid()

    hits = pygame.sprite.spritecollide(player, powerups, False)
    for hit in hits:
        if not player.hidden and not player.invisible:
            if hit.type == 'shield':
                if player.lives < 3:
                    heal_sound.play()
                    player.lives += 1        
                else:
                    repair_sound.play()
                    
            if hit.type == 'bomb':
                asteroids_list = asteroids.sprites()
                a = random.choice(asteroids_list)
                expl_sound.play()
                score += 1
                expl = explosion.Explosion(a.rect.center, 'sm')
                all_sprites.add(expl)
                a.kill()
                create_asteroid()

            if hit.type == '5r':
                r5_sound.play()
                score += 5
            
            hit.kill()
            
    hits = pygame.sprite.spritecollide(player, asteroids, False, pygame.sprite.collide_circle)
    for hit in hits:
        if not player.hidden and not player.invisible:
            player.angle = 0
            expl_player_sound.play()
            death_explosion = explosion.Explosion(player.rect.center, 'player')
            all_sprites.add(death_explosion)
            player.hide()
            player.lives -= 1
        
            if player.lives <= 0:
                game_over = True
                died_sound.play()

            hit.kill()
            create_asteroid()
    
    all_sprites.update()

    screen.fill(settings.COLOR_BLACK)
    screen.blit(background, background_rect)
    all_sprites.draw(screen)
    functions.draw_text(screen, str(score), 28, settings.SCREEN_WIDTH / 2, 10)
    functions.draw_lives(screen, settings.SCREEN_WIDTH - 85, 15, player.lives, player_mini_img)
    pygame.display.flip()

pygame.quit()