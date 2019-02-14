from rest_framework import serializers

from django.contrib.auth.models import User
from .models import Emprendimiento
from .models import Evento
from .models import ComentarioEmprendimiento
from .models import Client




class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('username',)



class EmprendimientoSerializer(serializers.ModelSerializer):
    
    #user_id = serializers.CharField()

    class Meta:
        model = Emprendimiento
        fields = ('id',  'user_id','name', 'description', 'website','email','image', 'create_date')

    def __str__(self):
        return self.user_id

class ComentarioSerializer(serializers.ModelSerializer):
    
    
    class Meta:
        model = ComentarioEmprendimiento
        fields = ('id','user','comment', 'comment_user', 'create_date')





class ClientSerializer(serializers.ModelSerializer):

    class Meta:
        model = Client
        fields = ('username',)


class EventoSerializer(serializers.ModelSerializer):
    
    #username = serializers.CharField(read_only=True)

    class Meta:
        model = Evento
        fields = ('username', 'name','place','begin_date','image', 'start_hour', 'final_date','end_hour',
            'description','organizer')


        def create(self, validate_data):
            return Evento.objects.create(**validate_data)


class TokenSerializer(serializers.Serializer):
    """
    This serializer serializes the token data
    """
    token = serializers.CharField(max_length=255)



