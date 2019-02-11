from rest_framework import serializers

from .models import Evento
#from .models import Client

from django.contrib.auth.models import User
import base64
from .models import Client

from django.core.files.base import ContentFile


from drf_extra_fields.fields import Base64ImageField




class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username','password',)

    def create(self, validated_data):
        user = super(UserSerializer, self).create(validated_data)
        user.set_password(validated_data['password'])
        user.save()
        return user



class ClientSerializer(serializers.ModelSerializer):

    class Meta:
        model = Client
        fields = ('user',)

class EventoSerializer(serializers.ModelSerializer):
    
    #user_id = serializers.IntegerField()
    #user = serializers.SerializerMethodField()
    image = serializers.ImageField()
    
    #def get_user(self, obj):
    #    return obj.user.username

    class Meta:
        model = Evento
        fields = ('id', 'user', 'name','place','begin_date','image','start_hour', 'final_date','end_hour',
            'description','organizer','facebook','instagram','twitter')

    

class TokenSerializer(serializers.Serializer):
    """
    This serializer serializes the token data
    """
    token = serializers.CharField(max_length=255)



