from rest_framework import serializers
from django.contrib.auth.hashers import make_password



from django.contrib.auth.models import User
from .models import Emprendimiento
from .models import Evento
from .models import ComentarioEmprendimiento
#from .models import Client

class CreateUserSerializer(serializers.HyperlinkedModelSerializer):
    pass

class UserSerializer(serializers.HyperlinkedModelSerializer):

    #password = serializers.CharField(required = True, style = {'input_type':'password'}) 

    class Meta:
        model = User
        fields = ('username', 'password')


    def create(self, validate_data):
        user = User.objects.create_user(**validate_data)
        return user

class EmprendimientoSerializer(serializers.ModelSerializer):
    

    user_id= serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')
    comments  = serializers.StringRelatedField(many = True)
    #comment = ComentarioSerializer()
    class Meta:
        model = Emprendimiento
        fields = ('id',  'user_id','name', 'description', 'website','email','image', 'create_date', 'comments')
        ordering = ['comments']
        depth = 2


    def __str__(self):
        return self.user_id

class ComentarioSerializer(serializers.ModelSerializer):
    
    comment_user = serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')
    name = serializers.SlugRelatedField(queryset = Emprendimiento.objects.all(), slug_field = 'name')


    class Meta:
        model = ComentarioEmprendimiento
        fields = ('id','comment', 'name', 'create_date', 'comment_user')
        depth = 1
   


class EventoSerializer(serializers.ModelSerializer):
    
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



