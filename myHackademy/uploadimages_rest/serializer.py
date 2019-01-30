from rest_framework import serializers
from eventos.models import Evento


class UploadImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Evento
        fields = ('pk', 'image',)

        