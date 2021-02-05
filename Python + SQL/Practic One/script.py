import sqlalchemy
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