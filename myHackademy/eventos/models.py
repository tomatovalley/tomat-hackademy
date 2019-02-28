from django.db import models
from django.core.files.base import ContentFile

# Create your models here.
import datetime
from django.contrib.auth.models import User
from django.utils import timezone
import json


class Evento(models.Model):

    username  = models.ForeignKey(User, related_name ='eventos', blank=True, null=True, on_delete = models.CASCADE)
    email = models.EmailField(max_length = 50)
    name = models.TextField(max_length=200, default="")
    place = models.TextField(max_length=200, default="")
    begin_date = models.DateField( default = datetime.date.today )
    image = models.FileField(upload_to= 'images',null=True, blank=True)
    start_hour = models.TimeField( default = datetime.time)
    final_date = models.DateField(blank= True, null=True,default = datetime.date.today)
    end_hour = models.TimeField(blank=True,null=True, default = datetime.time)
    description = models.TextField( blank=True, null=True, max_length = 500, default="" )
    organizer = models.TextField(blank=True, null=True, max_length = 50, default="")
    #data__ = models.TextField()
   


    def validate_unique(self, exclude=None):
        if Evento.objects.filter(name=self.name).exists():
            raise ValidationEror('Un evento con ese nombre ya existe')

    def save(self, *args, **kwargs):

        self.validate_unique()
        super(Evento, self).save(*args, **kwargs)


    def __str__(self):

        return '{}{}'.format(self.name, self.username)

"""Modelo Emprendimiento"""


class Emprendimiento(models.Model):

    user_id = models.ForeignKey(User, on_delete = models.CASCADE)
    name = models.TextField(max_length=200, default="")
    description = models.TextField(max_length = 500, default="")
    website = models.URLField(max_length=100, default="")
    email = models.EmailField(max_length=50)
    image = models.ImageField(upload_to='starUp')
    create_date = models.DateField(default = datetime.date.today)

    def validate_unique(self, exclude = None):

        if Emprendimiento.objects.filter(name=self.name).exists():
            raise ValidationEror("El Emprendimiento ya existe")

    def save(self, *args, **kwargs):

        self.validate_unique()
        super(Emprendimiento, self).save(*args, **kwargs)

    def __str__(self):
        return self.name
   
"""Modelo Comentarios Emprendimiento"""

class ComentarioEmprendimiento(models.Model):

    comment_user= models.ForeignKey(User, related_name='User', on_delete = models.CASCADE)
    name = models.ForeignKey(Emprendimiento, related_name = 'comments', on_delete= models.CASCADE)
    comment = models.TextField(max_length= 250, default="")
    create_date = models.DateField(default = datetime.date.today)


    def __str__(self):


        dic = {}
        username_ = self.comment_user
        comment = self.comment
        date_ = self.create_date

        #dic = {"username":[str(username_ )],"Comentarios":[comment],"Fecha":[date_]} 
         #return f"Username: {self.comment_user}  {self.comment}   {self.create_date}"
        '''creara error '''
    
        return "{}:  {} {}".format(username_, comment, date_)