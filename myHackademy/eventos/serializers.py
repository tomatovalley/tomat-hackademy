from rest_framework import serializers
from django.contrib.auth.hashers import make_password


from drf_extra_fields.fields import Base64ImageField
from django.contrib.auth.models import User
from .models import Emprendimiento
from .models import Evento
from .models import ComentarioEmprendimiento
'''
from django.core.files.base import ContentFile
import base64
import six
import uuid
import imghdr



class Base64ImageField(serializers.ImageField):

    def to_internal_value(self, data):

        # Check if this is a base64 string
        if isinstance(data, six.string_types):
            # Check if the base64 string is in the "data:" format
            if 'data:' in data and ';base64,' in data:
                # Break out the header from the base64 content
                header, data = data.split(';base64,')

            # Try to decode the file. Return validation error if it fails.
            try:
                decoded_file = base64.b64decode(data)
            except TypeError:
                self.fail('invalid_image')

            # Generate file name:
            file_name = str(uuid.uuid4())[:12] # 12 characters are more than enough.
            # Get the file name extension:
            file_extension = self.get_file_extension(file_name, decoded_file)

            complete_file_name = "%s.%s" % (file_name, file_extension, )

            data = ContentFile(decoded_file, name=complete_file_name)

        return super(Base64ImageField, self).to_internal_value(data)

    def get_file_extension(self, file_name, decoded_file):

        extension = imghdr.what(file_name, decoded_file)
        extension = "jpg" if extension == "jpeg" else extension

        return extension
'''
class TokenSerializer(serializers.Serializer):
    """
    This serializer serializes the token data
    """
    #user = UserSerializer()
    token = serializers.CharField(max_length=255)



class CreateUserSerializer(serializers.HyperlinkedModelSerializer):
    pass

class UserSerializer(serializers.ModelSerializer):

    #password = serializers.CharField(required = True, style = {'input_type':'password'}) 
    #token = TokenSerializer()
    class Meta:
        model = User
        fields = ( 'username', 'password')


    def create(self, validate_data):
        user = User.objects.create_user(**validate_data)
        return user

class EmprendimientoSerializer(serializers.ModelSerializer):
    

    user_id= serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')
    comments  = serializers.StringRelatedField(many = True)
    
    class Meta:
        model = Emprendimiento
        fields = ('id', 'user_id','name', 'description', 'website','email','image', 'create_date', 'comments')
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
   
    def __str__(self):

        return self.comment_user, self.id

class EventoSerializer(serializers.ModelSerializer):
    
    #image = Base64ImageField(max_length = None) 

    username = serializers.SlugRelatedField(queryset = User.objects.all(), slug_field = 'username')  
    
    class Meta:
        model = Evento
        fields = ('username', 'name','place','begin_date','image', 'start_hour', 'final_date','end_hour',
            'description','organizer')


        def create(self, validate_data):
            return Evento.objects.create(**validate_data)

    def __str__(self):
        return self.username

