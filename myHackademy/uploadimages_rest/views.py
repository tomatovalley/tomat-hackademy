from django.shortcuts import render
from rest_framework import viewsets
# Create your views here.

from uploadimages_rest.serializer import UploadImageSerializer
from eventos.models import Evento


class UploadImageViewSet(viewsets.ModelViewSet):

    queryset= Evento.objects.all()
    serializer_class = UploadImageSerializer
