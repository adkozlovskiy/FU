
# coding: utf-8

# In[1]:


import sqlalchemy
import datetime
from sqlalchemy import create_engine

engine = create_engine('sqlite:///listings.db')

from sqlalchemy import (MetaData, Table, Column, Integer, Numeric, String, DateTime, Boolean, 
                        ForeignKey, create_engine)
                        
metadata = MetaData()

employees=Table('employees',metadata,
               Column('id',Integer(),primary_key=True),
               Column('name',String(32),nullable=False),
               Column('surname',String(32),nullable=False),
               Column('birthdate',DateTime(),nullable=False),
               Column('position',String(32),nullable=False),
               Column('salary',Integer(),nullable=False),
               Column('contract_date',DateTime(),nullable=False),
               extend_existing=True
              )

personal_info=Table('personal_info',metadata,
               Column('id',Integer(),primary_key=True),
               Column('city',String(32),nullable=False),
               Column('birthdate',DateTime(),nullable=False),
               Column('adress_one',String(32),nullable=False),
               Column('adress_two',String(32)),
               Column('postal_code',Integer(),nullable=False),
               Column('phone_one',String(32),nullable=False),
               Column('phone_two',String(32)),
               extend_existing=True
              )

clients=Table('clients',metadata,
               Column('id',Integer(),primary_key=True),
               Column('name',String(32),nullable=False),
               Column('surname',String(32),nullable=False),
               Column('online_client',Boolean(), nullable=False, default=False),
               Column('personal_info_id',Integer(), ForeignKey('personal_info.id')),
               extend_existing=True
              )

online_login_data=Table('online_login_data',metadata,
               Column('id',Integer(),primary_key=True),
               Column('login',String(32),nullable=False),
               Column('password',String(32),nullable=False),
               Column('keyword',String(32)),
               Column('client_id',Integer(), ForeignKey('clients.id')),
               extend_existing=True
              )

accounts=Table('accounts',metadata,
               Column('number',Integer(),primary_key=True),
               Column('type',Integer(),nullable=False),
               Column('balance',Integer(), nullable=False, default=0),               
               Column('code',String(32), nullable=False),
               extend_existing=True
              )

cards=Table('cards',metadata,
               Column('id',Integer(),primary_key=True),
               Column('card_number',String(32), nullable=False),
               Column('type',Integer(), nullable=False, default=0),
               Column('issue_date', DateTime(), nullable=False),
               Column('exp_date', DateTime(), nullable=False),
               Column('cvv', Integer(), nullable=False),
               Column('blocked',Boolean()),
               Column('client_id',Integer(), ForeignKey('clients.id')),
               Column('account_number',Integer(), ForeignKey('accounts.number')),
               extend_existing=True
              )

client_account=Table('client_account',metadata,
               Column('id',Integer(),primary_key=True),
               Column('client_id',Integer(), ForeignKey('clients.id')),
               Column('account_id',Integer(), ForeignKey('accounts.number')),
               extend_existing=True
              )

metadata.create_all(engine)


# In[ ]:


connection=engine.connect()


# In[3]:


ins = employees.insert().values(
    id=1,
    name='Никита',
    surname='Гладкий',
    birthdate=datetime.date(2001, 3, 2),
    position='Бухгалтер',
    salary=100,
    contract_date=datetime.date(2021, 2, 19)
)


# In[4]:


result=connection.execute(ins)
result.inserted_primary_key


# In[5]:


ins = employees.insert().values(
    id=2,
    name='Артем',
    surname='Теплов',
    birthdate=datetime.date(2000, 12, 19),
    position='Охранник',
    salary=250,
    contract_date=datetime.date(2020, 9, 6)
)


# In[6]:


result=connection.execute(ins)
result.inserted_primary_key


# In[20]:


ins = employees.insert().values(
    id=3,
    name='Максим',
    surname='Касимов',
    birthdate=datetime.date(2001, 1, 29),
    position='Охранник',
    salary=350,
    contract_date=datetime.date(2021, 1, 11)
)


# In[21]:


result=connection.execute(ins)
result.inserted_primary_key


# In[7]:


ins = personal_info.insert().values(
    id=1,
    city='Москва',
    birthdate=datetime.date(2001, 3, 2),
    adress_one='Общага',
    postal_code=139174,
    phone_one='89640746281'
)


# In[8]:


result=connection.execute(ins)
result.inserted_primary_key


# In[22]:


ins = personal_info.insert().values(
    id=2,
    city='Москва',
    birthdate=datetime.date(1972, 8, 22),
    adress_one='Щербаковская 38, к.504',
    postal_code=441231,
    phone_one='89859876555'
)


# In[23]:


result=connection.execute(ins)
result.inserted_primary_key


# In[9]:


ins = clients.insert().values(
    id=1,
    name='Катя',
    surname='Прищепа',
    online_client=True,
    personal_info_id=1
)


# In[10]:


result=connection.execute(ins)
result.inserted_primary_key


# In[24]:


ins = clients.insert().values(
    id=2,
    name='Дмитрий',
    surname='Кораблев',
    online_client=False,
    personal_info_id=2
)


# In[25]:


result=connection.execute(ins)
result.inserted_primary_key


# In[15]:


ins = accounts.insert().values(
    number=1,
    type=0,
    code='ZIP0921'
)


# In[16]:


result=connection.execute(ins)
result.inserted_primary_key


# In[26]:


ins = accounts.insert().values(
    number=2,
    type=2,
    code='ZIP1311'
)


# In[27]:


result=connection.execute(ins)
result.inserted_primary_key


# In[17]:


ins = cards.insert().values(
    id=1,
    card_number='3313 1331 4416 1340',
    type=0,
    issue_date=datetime.date(2019, 6, 6),
    exp_date=datetime.date(2023, 6, 7),
    cvv=331,
    blocked=False,
    client_id=1,
    account_number=1
)


# In[18]:


result=connection.execute(ins)
result.inserted_primary_key


# In[28]:


ins = cards.insert().values(
    id=2,
    card_number='1593 5718 0013 0001',
    type=0,
    issue_date=datetime.date(2016, 12, 15),
    exp_date=datetime.date(2020, 6, 7),
    cvv=901,
    blocked=True,
    client_id=2,
    account_number=2
)


# In[29]:


result=connection.execute(ins)
result.inserted_primary_key


# In[31]:


ins = client_account.insert().values(
    id=1,
    client_id=1,
    account_id=1
)


# In[32]:


result=connection.execute(ins)
result.inserted_primary_key


# In[35]:


ins = client_account.insert().values(
    id=2,
    client_id=2,
    account_id=2
)


# In[36]:


result=connection.execute(ins)
result.inserted_primary_key


# In[37]:


ins = online_login_data.insert().values(
    id=1,
    login='dubrovina',
    password='fvokdejvi9j29dj2envef0n92',
    keyword='Мурзик',
    client_id=1,
)


# In[38]:


result=connection.execute(ins)
result.inserted_primary_key


# In[52]:


from sqlalchemy.sql import select
import pandas as pd
s = select([personal_info])  
rp = connection.execute(s)
results = pd.DataFrame(rp.fetchall()) 
results


# In[53]:


s = select([clients])  
rp = connection.execute(s)
results = pd.DataFrame(rp.fetchall()) 
results


# In[54]:


s = select([accounts])  
rp = connection.execute(s)
results = pd.DataFrame(rp.fetchall()) 
results


# In[55]:


s = select([client_account])  
rp = connection.execute(s)
results = pd.DataFrame(rp.fetchall()) 
results


# In[56]:


s = select([online_login_data])  
rp = connection.execute(s)
results = pd.DataFrame(rp.fetchall()) 
results


# In[57]:


s = select([cards])  
rp = connection.execute(s)
results = pd.DataFrame(rp.fetchall()) 
results

