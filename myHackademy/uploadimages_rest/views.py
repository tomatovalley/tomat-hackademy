from django.shortcuts import render
from rest_framework import viewsets
# Create your views here.

from uploadimages_rest.serializer import UploadImageSerializer
from eventos.models import Evento
from rest_framework import permissions



class UploadImageViewSet(viewsets.ModelViewSet):

    permission_classes = (permissions.AllowAny,)

    queryset= Evento.objects.all()
    serializer_class = UploadImageSerializer
