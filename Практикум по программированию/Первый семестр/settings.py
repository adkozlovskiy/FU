''' Размеры окна '''
SCREEN_WIDTH = 1600
SCREEN_HEIGHT = 1000
FPS = 60 # Частота кадров

COLOR_WHITE = (255, 255, 255) # Код белого цвета
COLOR_BLACK = (0, 0, 0) # Код черного цвета
COLOR_RED = (255, 0, 0) # Код красного цвета
COLOR_GREEN = (0, 255, 0) # Код зеленого цвета
COLOR_BLUE = (0, 0, 255) # Код синего цвета
COLOR_YELLOW = (255, 255, 0) # Код желтого цвета

MIN_ASTEROID_SPEED_Y = 3 # Скорость минимальная по вертикали
MAX_ASTEROID_SPEED_Y = 7 # Скорость максимальная по вертикали
ASTEROID_SPEED_X = 4 # Скорость по горизонтали
ASTEROID_SIZE_MEDIUM = 70 # Размер среднего астероида
ASTEROID_SIZE_BIG = 90 # Размер большого астероида
PLAYER_SPEED = 8 # Скорость корабля
PLAYER_ANGLE_SPEED = 5 # Скорость поворота
ROCKET_SPEED = 10 # Скорость ракеты
COLISION_RADIUS = 22 # Радиус сферы коллизии
PLAYER_HP = 3 # Количество жизней
POW_CHANCE = 0.95 # 1 - Вероятность выпадения бонуса
ROCKET_LIFETIME = ROCKET_SPEED * 2 # Время жизни ракеты
MUSIC_VOLUME = 0.2 # Громкость музыки
ASTEROIDS_COUNT = 15 # Максимальное количество астероидов на игровом поле