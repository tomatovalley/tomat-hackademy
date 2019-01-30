from django.conf.urls import url, include
from rest_framework import routers
from django.urls import re_path

from uploadimages_rest.views import UploadImageViewSet

router = routers.DefaultRouter()
router.register('images', UploadImageViewSet, 'images')

urlpatterns= [
    re_path('', include(router.urls))
]