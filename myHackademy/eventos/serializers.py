from rest_framework import serializers

from django.contrib.auth.models import User
from .models import Emprendimiento
from .models import Evento
from .models import ComentarioEmprendimiento
from .models import Client
from .models import Comentario



class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('username',)






class EmprendimientoSerializer(serializers.ModelSerializer):
    

    user_id= serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')
    #comments = ComentarioSerializer(many = True)
    #comentario = ComentarioSerializer(many= True, read_only= True)
    comments  = serializers.StringRelatedField(many = True)

    class Meta:
        model = Emprendimiento
        fields = ('id',  'user_id','name', 'description', 'website','email','image', 'create_date', 'comments')
        depth = 1


    def __str__(self):
        return self.user_id

class ComentarioSerializer(serializers.ModelSerializer):
    
    comment_user = serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')
    name = serializers.SlugRelatedField(queryset = Emprendimiento.objects.all(), slug_field = 'name')


    class Meta:
        model = ComentarioEmprendimiento
        fields = ('id','comment_user','comment', 'name', 'create_date')
        depth = 1


   

class ClientSerializer(serializers.ModelSerializer):

    class Meta:
        model = Client
        fields = ('username',)


class EventoSerializer(serializers.ModelSerializer):
    
    username = serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')

    class Meta:
        model = Evento
        fields = ('username', 'name','place','begin_date','image', 'start_hour', 'final_date','end_hour',
            'description','organizer')


        def create(self, validate_data):
            return Evento.objects.create(**validate_data)


class CommentSerializer(serializers.ModelSerializer):

    #emp = EmprendimientoSerializer(many= True, read_only= True, source= 'Emprendimiento')

    class Meta:
        model= Comentario
        fields = ('comment', 'emprendimiento')
        depth = 1


class TokenSerializer(serializers.Serializer):
    """
    This serializer serializes the token data
    """
    token = serializers.CharField(max_length=255)



