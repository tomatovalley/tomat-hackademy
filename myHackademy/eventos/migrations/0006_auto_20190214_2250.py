# Generated by Django 2.1.5 on 2019-02-14 22:50

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('eventos', '0005_auto_20190214_2241'),
    ]

    operations = [
        migrations.RenameField(
            model_name='comentarioemprendimiento',
            old_name='user',
            new_name='comment_user',
        ),
    ]
