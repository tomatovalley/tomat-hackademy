from django.db import models

# Create your models here.
import datetime
from django.contrib.auth.models import User
from django.utils import timezone



class Client(models.Model):

    user = models.OneToOneField(User, on_delete = models.CASCADE)


class Evento(models.Model):

    user  = models.ForeignKey(User, related_name ='eventos', on_delete = models.CASCADE)
    name = models.TextField(max_length=200, default="")
    place = models.TextField(max_length=200, default="")
    begin_date = models.DateField( default = datetime.date.today )
    image = models.ImageField(upload_to= 'images')
    start_hour = models.TimeField( default = datetime.time)
    final_date = models.DateField(default = datetime.date.today)
    end_hour = models.TimeField( default = datetime.time)
    description = models.TextField( max_length = 500, default="" )
    organizer = models.TextField(max_length = 50, default="")
    facebook = models.URLField(max_length = 100, default="")
    instagram = models.URLField(max_length = 100, default="")
    twitter = models.URLField(max_length = 100, default="")
    
    def __str__(self):

        return self.name