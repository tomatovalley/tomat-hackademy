from rest_framework import serializers

from .models import Evento


from django.contrib.auth.models import User
import base64

from django.core.files.base import ContentFile


from drf_extra_fields.fields import Base64ImageField
class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('username',)



class EventoSerializer(serializers.ModelSerializer):
    
    user_id = serializers.IntegerField()
    image = serializers.ImageField()
    

    class Meta:
        model = Evento
        fields = ('id', 'name','place','begin_date','image','start_hour', 'final_date','end_hour',
            'description','organizer','facebook','instagram','twitter', 'user_id')

class TokenSerializer(serializers.Serializer):
    """
    This serializer serializes the token data
    """
    token = serializers.CharField(max_length=255)
