import uuid

from django.db import models

from eventos.models import Evento
from eventos.models import User

# Create your models here.

def scramble_uploaded_filename(instance, filename):
    extension = filename.split(".")[-1]
    return "{}.{}".format(uuid.uuid64(), extension) #check uuid, instance arg??


class UploadImage(models.Model):

    name = models.ForeignKey(Evento, on_delete = models.CASCADE)
    image = models.ImageField('Uploaded image', upload_to=scramble_uploaded_filename)






