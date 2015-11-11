package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldVisitor;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ASMSerializerFactory
  implements Opcodes
{
  private ASMClassLoader classLoader = new ASMClassLoader();
  private final AtomicLong seed = new AtomicLong();

  private void _after(MethodVisitor paramMethodVisitor, Context paramContext)
  {
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "writeAfter", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;C)C");
    paramMethodVisitor.visitVarInsn(54, paramContext.var("seperator"));
  }

  private void _apply(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramFieldInfo = paramFieldInfo.getFieldClass();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    if (paramFieldInfo == Byte.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("byte"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;B)Z");
      return;
    }
    if (paramFieldInfo == Short.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("short"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;S)Z");
      return;
    }
    if (paramFieldInfo == Integer.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;I)Z");
      return;
    }
    if (paramFieldInfo == Character.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("char"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;C)Z");
      return;
    }
    if (paramFieldInfo == Long.TYPE)
    {
      paramMethodVisitor.visitVarInsn(22, paramContext.var("long", 2));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;J)Z");
      return;
    }
    if (paramFieldInfo == Float.TYPE)
    {
      paramMethodVisitor.visitVarInsn(23, paramContext.var("float"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;F)Z");
      return;
    }
    if (paramFieldInfo == Double.TYPE)
    {
      paramMethodVisitor.visitVarInsn(24, paramContext.var("double", 2));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;D)Z");
      return;
    }
    if (paramFieldInfo == Boolean.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("boolean"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;B)Z");
      return;
    }
    if (paramFieldInfo == BigDecimal.class)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("decimal"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
      return;
    }
    if (paramFieldInfo == String.class)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("string"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
      return;
    }
    if (paramFieldInfo.isEnum())
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("enum"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
      return;
    }
    if (List.class.isAssignableFrom(paramFieldInfo))
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
      return;
    }
    paramMethodVisitor.visitVarInsn(25, paramContext.var("object"));
    paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
  }

  private void _before(MethodVisitor paramMethodVisitor, Context paramContext)
  {
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "writeBefore", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;C)C");
    paramMethodVisitor.visitVarInsn(54, paramContext.var("seperator"));
  }

  private void _boolean(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("boolean"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("boolean"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Z)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _byte(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("byte"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("byte"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _char(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("char"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("char"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;C)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _decimal(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(58, paramContext.var("decimal"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    Label localLabel1 = new Label();
    Label localLabel2 = new Label();
    Label localLabel3 = new Label();
    paramMethodVisitor.visitLabel(localLabel1);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("decimal"));
    paramMethodVisitor.visitJumpInsn(199, localLabel2);
    _if_write_null(paramMethodVisitor, paramFieldInfo, paramContext);
    paramMethodVisitor.visitJumpInsn(167, localLabel3);
    paramMethodVisitor.visitLabel(localLabel2);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("decimal"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitJumpInsn(167, localLabel3);
    paramMethodVisitor.visitLabel(localLabel3);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _double(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(57, paramContext.var("double", 2));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(24, paramContext.var("double", 2));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;D)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _enum(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    int k = 0;
    int i = 0;
    paramClass = (JSONField)paramFieldInfo.getAnnotation(JSONField.class);
    if (paramClass != null)
    {
      paramClass = paramClass.serialzeFeatures();
      int m = paramClass.length;
      int j = 0;
      while (true)
      {
        k = i;
        if (j >= m)
          break;
        if (paramClass[j] == SerializerFeature.WriteEnumUsingToString)
          i = 1;
        j += 1;
      }
    }
    paramClass = new Label();
    Label localLabel1 = new Label();
    Label localLabel2 = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, localLabel2);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitTypeInsn(192, ASMUtils.getType(Enum.class));
    paramMethodVisitor.visitVarInsn(58, paramContext.var("enum"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, localLabel2);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("enum"));
    paramMethodVisitor.visitJumpInsn(199, paramClass);
    _if_write_null(paramMethodVisitor, paramFieldInfo, paramContext);
    paramMethodVisitor.visitJumpInsn(167, localLabel1);
    paramMethodVisitor.visitLabel(paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("enum"));
    if (k != 0)
    {
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(Object.class), "toString", "()Ljava/lang/String;");
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
    }
    while (true)
    {
      _seperator(paramMethodVisitor, paramContext);
      paramMethodVisitor.visitLabel(localLabel1);
      paramMethodVisitor.visitLabel(localLabel2);
      return;
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;L" + ASMUtils.getType(Enum.class) + ";)V");
    }
  }

  private void _filters(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext, Label paramLabel)
  {
    if ((paramFieldInfo.getField() != null) && (Modifier.isTransient(paramFieldInfo.getField().getModifiers())))
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitFieldInsn(178, ASMUtils.getType(SerializerFeature.class), "SkipTransientField", "L" + ASMUtils.getType(SerializerFeature.class) + ";");
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + ";" + ")Z");
      paramMethodVisitor.visitJumpInsn(154, paramLabel);
    }
    _apply(paramMethodVisitor, paramFieldInfo, paramContext);
    paramMethodVisitor.visitJumpInsn(153, paramLabel);
    _processKey(paramMethodVisitor, paramFieldInfo, paramContext);
    Label localLabel = new Label();
    _processValue(paramMethodVisitor, paramFieldInfo, paramContext);
    paramMethodVisitor.visitVarInsn(25, paramContext.original());
    paramMethodVisitor.visitVarInsn(25, paramContext.processValue());
    paramMethodVisitor.visitJumpInsn(165, localLabel);
    _writeObject(paramMethodVisitor, paramFieldInfo, paramContext, paramLabel);
    paramMethodVisitor.visitJumpInsn(167, paramLabel);
    paramMethodVisitor.visitLabel(localLabel);
  }

  private void _float(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(56, paramContext.var("float"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(23, paramContext.var("float"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;F)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _get(MethodVisitor paramMethodVisitor, Context paramContext, FieldInfo paramFieldInfo)
  {
    Method localMethod = paramFieldInfo.getMethod();
    if (localMethod != null)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("entity"));
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(localMethod.getDeclaringClass()), localMethod.getName(), ASMUtils.getDesc(localMethod));
      return;
    }
    paramMethodVisitor.visitVarInsn(25, paramContext.var("entity"));
    paramMethodVisitor.visitFieldInsn(180, ASMUtils.getType(paramFieldInfo.getDeclaringClass()), paramFieldInfo.getField().getName(), ASMUtils.getDesc(paramFieldInfo.getFieldClass()));
  }

  private void _if_write_null(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    Class localClass = paramFieldInfo.getFieldClass();
    Label localLabel4 = new Label();
    Label localLabel1 = new Label();
    Label localLabel2 = new Label();
    Label localLabel3 = new Label();
    paramMethodVisitor.visitLabel(localLabel4);
    int i2 = 0;
    int n = 0;
    int i5 = 0;
    int j = 0;
    int i6 = 0;
    int i = 0;
    int i3 = 0;
    int k = 0;
    int i4 = 0;
    int m = 0;
    paramFieldInfo = (JSONField)paramFieldInfo.getAnnotation(JSONField.class);
    if (paramFieldInfo != null)
    {
      paramFieldInfo = paramFieldInfo.serialzeFeatures();
      int i7 = paramFieldInfo.length;
      int i1 = 0;
      i2 = n;
      i3 = k;
      i4 = m;
      i5 = j;
      i6 = i;
      if (i1 < i7)
      {
        localLabel4 = paramFieldInfo[i1];
        if (localLabel4 == SerializerFeature.WriteMapNullValue)
        {
          i2 = 1;
          i5 = i;
          i4 = j;
          i3 = k;
        }
        while (true)
        {
          i1 += 1;
          n = i2;
          k = i3;
          j = i4;
          i = i5;
          break;
          if (localLabel4 == SerializerFeature.WriteNullNumberAsZero)
          {
            i4 = 1;
            i2 = n;
            i3 = k;
            i5 = i;
          }
          else if (localLabel4 == SerializerFeature.WriteNullStringAsEmpty)
          {
            i5 = 1;
            i2 = n;
            i3 = k;
            i4 = j;
          }
          else if (localLabel4 == SerializerFeature.WriteNullBooleanAsFalse)
          {
            i3 = 1;
            i2 = n;
            i4 = j;
            i5 = i;
          }
          else
          {
            i2 = n;
            i3 = k;
            i4 = j;
            i5 = i;
            if (localLabel4 == SerializerFeature.WriteNullListAsEmpty)
            {
              m = 1;
              i2 = n;
              i3 = k;
              i4 = j;
              i5 = i;
            }
          }
        }
      }
    }
    if (i2 == 0)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitFieldInsn(178, ASMUtils.getType(SerializerFeature.class), "WriteMapNullValue", "L" + ASMUtils.getType(SerializerFeature.class) + ";");
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + ";" + ")Z");
      paramMethodVisitor.visitJumpInsn(153, localLabel1);
    }
    paramMethodVisitor.visitLabel(localLabel2);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    if ((localClass == String.class) || (localClass == Character.class))
      if (i6 != 0)
      {
        paramMethodVisitor.visitLdcInsn("");
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
      }
    while (true)
    {
      _seperator(paramMethodVisitor, paramContext);
      paramMethodVisitor.visitJumpInsn(167, localLabel3);
      paramMethodVisitor.visitLabel(localLabel1);
      paramMethodVisitor.visitLabel(localLabel3);
      return;
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldNullString", "(CLjava/lang/String;)V");
      continue;
      if (Number.class.isAssignableFrom(localClass))
      {
        if (i5 != 0)
        {
          paramMethodVisitor.visitInsn(3);
          paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
        }
        else
        {
          paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldNullNumber", "(CLjava/lang/String;)V");
        }
      }
      else if (localClass == Boolean.class)
      {
        if (i3 != 0)
        {
          paramMethodVisitor.visitInsn(3);
          paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Z)V");
        }
        else
        {
          paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldNullBoolean", "(CLjava/lang/String;)V");
        }
      }
      else if ((Collection.class.isAssignableFrom(localClass)) || (localClass.isArray()))
      {
        if (i4 != 0)
          paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldEmptyList", "(CLjava/lang/String;)V");
        else
          paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldNullList", "(CLjava/lang/String;)V");
      }
      else
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldNull", "(CLjava/lang/String;)V");
    }
  }

  private void _int(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("int"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _list(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = paramFieldInfo.getFieldType();
    if ((paramClass instanceof Class));
    Class localClass;
    for (paramClass = Object.class; ; paramClass = ((java.lang.reflect.ParameterizedType)paramClass).getActualTypeArguments()[0])
    {
      localClass = null;
      if ((paramClass instanceof Class))
        localClass = (Class)paramClass;
      Label localLabel1 = new Label();
      Label localLabel3 = new Label();
      Label localLabel4 = new Label();
      Label localLabel2 = new Label();
      paramMethodVisitor.visitLabel(localLabel3);
      _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, localLabel1);
      _get(paramMethodVisitor, paramContext, paramFieldInfo);
      paramMethodVisitor.visitTypeInsn(192, ASMUtils.getType(List.class));
      paramMethodVisitor.visitVarInsn(58, paramContext.var("list"));
      _filters(paramMethodVisitor, paramFieldInfo, paramContext, localLabel1);
      paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      paramMethodVisitor.visitJumpInsn(199, localLabel4);
      _if_write_null(paramMethodVisitor, paramFieldInfo, paramContext);
      paramMethodVisitor.visitJumpInsn(167, localLabel2);
      paramMethodVisitor.visitLabel(localLabel4);
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldName", "(Ljava/lang/String;)V");
      paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      paramMethodVisitor.visitMethodInsn(185, ASMUtils.getType(List.class), "size", "()I");
      paramMethodVisitor.visitVarInsn(54, paramContext.var("int"));
      localLabel3 = new Label();
      localLabel4 = new Label();
      paramFieldInfo = new Label();
      paramMethodVisitor.visitLabel(localLabel3);
      paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
      paramMethodVisitor.visitInsn(3);
      paramMethodVisitor.visitJumpInsn(160, localLabel4);
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitLdcInsn("[]");
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(Ljava/lang/String;)V");
      paramMethodVisitor.visitJumpInsn(167, paramFieldInfo);
      paramMethodVisitor.visitLabel(localLabel4);
      paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
      paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(16, 91);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
      paramMethodVisitor.visitInsn(1);
      paramMethodVisitor.visitTypeInsn(192, ASMUtils.getType(ObjectSerializer.class));
      paramMethodVisitor.visitVarInsn(58, paramContext.var("list_ser"));
      localLabel3 = new Label();
      localLabel4 = new Label();
      paramMethodVisitor.visitInsn(3);
      paramMethodVisitor.visitVarInsn(54, paramContext.var("i"));
      paramMethodVisitor.visitLabel(localLabel3);
      paramMethodVisitor.visitVarInsn(21, paramContext.var("i"));
      paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
      paramMethodVisitor.visitInsn(4);
      paramMethodVisitor.visitInsn(100);
      paramMethodVisitor.visitJumpInsn(162, localLabel4);
      if (paramClass != String.class)
        break;
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      paramMethodVisitor.visitVarInsn(21, paramContext.var("i"));
      paramMethodVisitor.visitMethodInsn(185, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
      paramMethodVisitor.visitTypeInsn(192, ASMUtils.getType(String.class));
      paramMethodVisitor.visitVarInsn(16, 44);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeString", "(Ljava/lang/String;C)V");
      paramMethodVisitor.visitIincInsn(paramContext.var("i"), 1);
      paramMethodVisitor.visitJumpInsn(167, localLabel3);
      paramMethodVisitor.visitLabel(localLabel4);
      if (paramClass != String.class)
        break label1298;
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
      paramMethodVisitor.visitInsn(4);
      paramMethodVisitor.visitInsn(100);
      paramMethodVisitor.visitMethodInsn(185, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
      paramMethodVisitor.visitTypeInsn(192, ASMUtils.getType(String.class));
      paramMethodVisitor.visitVarInsn(16, 93);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeString", "(Ljava/lang/String;C)V");
      paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "popContext", "()V");
      paramMethodVisitor.visitLabel(paramFieldInfo);
      _seperator(paramMethodVisitor, paramContext);
      paramMethodVisitor.visitLabel(localLabel2);
      paramMethodVisitor.visitLabel(localLabel1);
      return;
    }
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("i"));
    paramMethodVisitor.visitMethodInsn(185, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
    paramMethodVisitor.visitVarInsn(21, paramContext.var("i"));
    paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), "valueOf", "(I)Ljava/lang/Integer;");
    if ((localClass != null) && (Modifier.isPublic(localClass.getModifiers())))
    {
      paramMethodVisitor.visitLdcInsn(Type.getType(ASMUtils.getDesc((Class)paramClass)));
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
    }
    while (true)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(16, 44);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
      break;
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
    }
    label1298: paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("i"));
    paramMethodVisitor.visitMethodInsn(185, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
    paramMethodVisitor.visitVarInsn(21, paramContext.var("i"));
    paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), "valueOf", "(I)Ljava/lang/Integer;");
    if ((localClass != null) && (Modifier.isPublic(localClass.getModifiers())))
    {
      paramMethodVisitor.visitLdcInsn(Type.getType(ASMUtils.getDesc((Class)paramClass)));
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
    }
    while (true)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(16, 93);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
      break;
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
    }
  }

  private void _long(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(55, paramContext.var("long", 2));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(22, paramContext.var("long", 2));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;J)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _nameApply(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext, Label paramLabel)
  {
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "applyName", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;)Z");
    paramMethodVisitor.visitJumpInsn(153, paramLabel);
  }

  private void _object(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(58, paramContext.var("object"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _writeObject(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _processKey(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramFieldInfo = paramFieldInfo.getFieldClass();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    if (paramFieldInfo == Byte.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("byte"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;B)Ljava/lang/String;");
    }
    while (true)
    {
      paramMethodVisitor.visitVarInsn(58, paramContext.fieldName());
      return;
      if (paramFieldInfo == Short.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("short"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;S)Ljava/lang/String;");
      }
      else if (paramFieldInfo == Integer.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;I)Ljava/lang/String;");
      }
      else if (paramFieldInfo == Character.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("char"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;C)Ljava/lang/String;");
      }
      else if (paramFieldInfo == Long.TYPE)
      {
        paramMethodVisitor.visitVarInsn(22, paramContext.var("long", 2));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;J)Ljava/lang/String;");
      }
      else if (paramFieldInfo == Float.TYPE)
      {
        paramMethodVisitor.visitVarInsn(23, paramContext.var("float"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;F)Ljava/lang/String;");
      }
      else if (paramFieldInfo == Double.TYPE)
      {
        paramMethodVisitor.visitVarInsn(24, paramContext.var("double", 2));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;D)Ljava/lang/String;");
      }
      else if (paramFieldInfo == Boolean.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("boolean"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Z)Ljava/lang/String;");
      }
      else if (paramFieldInfo == BigDecimal.class)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("decimal"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
      }
      else if (paramFieldInfo == String.class)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("string"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
      }
      else if (paramFieldInfo.isEnum())
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("enum"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
      }
      else if (List.class.isAssignableFrom(paramFieldInfo))
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
      }
      else
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("object"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
      }
    }
  }

  private void _processValue(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramFieldInfo = paramFieldInfo.getFieldClass();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    if (paramFieldInfo == Byte.TYPE)
    {
      paramMethodVisitor.visitVarInsn(21, paramContext.var("byte"));
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Byte.class), "valueOf", "(B)Ljava/lang/Byte;");
    }
    while (true)
    {
      paramMethodVisitor.visitVarInsn(58, paramContext.original());
      paramMethodVisitor.visitVarInsn(25, paramContext.original());
      paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processValue", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
      paramMethodVisitor.visitVarInsn(58, paramContext.processValue());
      return;
      if (paramFieldInfo == Short.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("short"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Short.class), "valueOf", "(S)Ljava/lang/Short;");
      }
      else if (paramFieldInfo == Integer.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("int"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), "valueOf", "(I)Ljava/lang/Integer;");
      }
      else if (paramFieldInfo == Character.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("char"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Character.class), "valueOf", "(C)Ljava/lang/Character;");
      }
      else if (paramFieldInfo == Long.TYPE)
      {
        paramMethodVisitor.visitVarInsn(22, paramContext.var("long", 2));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Long.class), "valueOf", "(J)Ljava/lang/Long;");
      }
      else if (paramFieldInfo == Float.TYPE)
      {
        paramMethodVisitor.visitVarInsn(23, paramContext.var("float"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Float.class), "valueOf", "(F)Ljava/lang/Float;");
      }
      else if (paramFieldInfo == Double.TYPE)
      {
        paramMethodVisitor.visitVarInsn(24, paramContext.var("double", 2));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Double.class), "valueOf", "(D)Ljava/lang/Double;");
      }
      else if (paramFieldInfo == Boolean.TYPE)
      {
        paramMethodVisitor.visitVarInsn(21, paramContext.var("boolean"));
        paramMethodVisitor.visitMethodInsn(184, ASMUtils.getType(Boolean.class), "valueOf", "(Z)Ljava/lang/Boolean;");
      }
      else if (paramFieldInfo == BigDecimal.class)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("decimal"));
      }
      else if (paramFieldInfo == String.class)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("string"));
      }
      else if (paramFieldInfo.isEnum())
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("enum"));
      }
      else if (List.class.isAssignableFrom(paramFieldInfo))
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("list"));
      }
      else
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("object"));
      }
    }
  }

  private void _seperator(MethodVisitor paramMethodVisitor, Context paramContext)
  {
    paramMethodVisitor.visitVarInsn(16, 44);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("seperator"));
  }

  private void _short(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("short"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(21, paramContext.var("short"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _string(Class<?> paramClass, MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext)
  {
    paramClass = new Label();
    _nameApply(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    _get(paramMethodVisitor, paramContext, paramFieldInfo);
    paramMethodVisitor.visitVarInsn(58, paramContext.var("string"));
    _filters(paramMethodVisitor, paramFieldInfo, paramContext, paramClass);
    Label localLabel1 = new Label();
    Label localLabel2 = new Label();
    paramMethodVisitor.visitVarInsn(25, paramContext.var("string"));
    paramMethodVisitor.visitJumpInsn(199, localLabel1);
    _if_write_null(paramMethodVisitor, paramFieldInfo, paramContext);
    paramMethodVisitor.visitJumpInsn(167, localLabel2);
    paramMethodVisitor.visitLabel(localLabel1);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("string"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
    _seperator(paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel(localLabel2);
    paramMethodVisitor.visitLabel(paramClass);
  }

  private void _writeObject(MethodVisitor paramMethodVisitor, FieldInfo paramFieldInfo, Context paramContext, Label paramLabel)
  {
    String str = paramFieldInfo.getFormat();
    Label localLabel = new Label();
    paramMethodVisitor.visitVarInsn(25, paramContext.processValue());
    paramMethodVisitor.visitJumpInsn(199, localLabel);
    _if_write_null(paramMethodVisitor, paramFieldInfo, paramContext);
    paramMethodVisitor.visitJumpInsn(167, paramLabel);
    paramMethodVisitor.visitLabel(localLabel);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFieldName", "(Ljava/lang/String;)V");
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.processValue());
    if (str != null)
    {
      paramMethodVisitor.visitLdcInsn(str);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
    }
    while (true)
    {
      _seperator(paramMethodVisitor, paramContext);
      return;
      paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
      if (((paramFieldInfo.getFieldType() instanceof Class)) && (((Class)paramFieldInfo.getFieldType()).isPrimitive()))
      {
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
      }
      else
      {
        paramMethodVisitor.visitVarInsn(25, 0);
        paramMethodVisitor.visitFieldInsn(180, paramContext.getClassName(), paramFieldInfo.getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;");
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
      }
    }
  }

  private void generateWriteAsArray(Class<?> paramClass, MethodVisitor paramMethodVisitor, List<FieldInfo> paramList, Context paramContext)
    throws Exception
  {
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(16, 91);
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
    int k = paramList.size();
    if (k == 0)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(16, 93);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
      return;
    }
    int i = 0;
    label107: int j;
    if (i < k)
    {
      if (i != k - 1)
        break label254;
      j = 93;
      label127: paramClass = (FieldInfo)paramList.get(i);
      localObject = paramClass.getFieldClass();
      paramMethodVisitor.visitLdcInsn(paramClass.getName());
      paramMethodVisitor.visitVarInsn(58, paramContext.fieldName());
      if ((localObject != Byte.TYPE) && (localObject != Short.TYPE) && (localObject != Integer.TYPE))
        break label261;
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      _get(paramMethodVisitor, paramContext, paramClass);
      paramMethodVisitor.visitVarInsn(16, j);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeIntAndChar", "(IC)V");
    }
    while (true)
    {
      i += 1;
      break label107;
      break;
      label254: j = 44;
      break label127;
      label261: if (localObject == Long.TYPE)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeLongAndChar", "(JC)V");
      }
      else if (localObject == Float.TYPE)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeFloatAndChar", "(FC)V");
      }
      else if (localObject == Double.TYPE)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeDoubleAndChar", "(DC)V");
      }
      else if (localObject == Boolean.TYPE)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeBooleanAndChar", "(ZC)V");
      }
      else if (localObject == Character.TYPE)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeCharacterAndChar", "(CC)V");
      }
      else if (localObject == String.class)
      {
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeString", "(Ljava/lang/String;C)V");
      }
      else
      {
        if (!((Class)localObject).isEnum())
          break label708;
        paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
        _get(paramMethodVisitor, paramContext, paramClass);
        paramMethodVisitor.visitVarInsn(16, j);
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "writeEnum", "(Ljava/lang/Enum;C)V");
      }
    }
    label708: Object localObject = paramClass.getFormat();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    _get(paramMethodVisitor, paramContext, paramClass);
    if (localObject != null)
    {
      paramMethodVisitor.visitLdcInsn(localObject);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
    }
    while (true)
    {
      paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
      paramMethodVisitor.visitVarInsn(16, j);
      paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
      break;
      paramMethodVisitor.visitVarInsn(25, paramContext.fieldName());
      if (((paramClass.getFieldType() instanceof Class)) && (((Class)paramClass.getFieldType()).isPrimitive()))
      {
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
      }
      else
      {
        paramMethodVisitor.visitVarInsn(25, 0);
        paramMethodVisitor.visitFieldInsn(180, paramContext.getClassName(), paramClass.getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;");
        paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
      }
    }
  }

  private void generateWriteMethod(Class<?> paramClass, MethodVisitor paramMethodVisitor, List<FieldInfo> paramList, Context paramContext)
    throws Exception
  {
    Label localLabel1 = new Label();
    int j = paramList.size();
    Object localObject1 = new Label();
    Object localObject2 = new Label();
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitFieldInsn(178, ASMUtils.getType(SerializerFeature.class), "PrettyFormat", "L" + ASMUtils.getType(SerializerFeature.class) + ";");
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + ";" + ")Z");
    paramMethodVisitor.visitJumpInsn(153, (Label)localObject1);
    paramMethodVisitor.visitVarInsn(25, 0);
    paramMethodVisitor.visitFieldInsn(180, paramContext.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
    paramMethodVisitor.visitJumpInsn(199, (Label)localObject2);
    initNature(paramClass, paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel((Label)localObject2);
    paramMethodVisitor.visitVarInsn(25, 0);
    paramMethodVisitor.visitFieldInsn(180, paramContext.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
    paramMethodVisitor.visitVarInsn(25, 1);
    paramMethodVisitor.visitVarInsn(25, 2);
    paramMethodVisitor.visitVarInsn(25, 3);
    paramMethodVisitor.visitVarInsn(25, 4);
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JavaBeanSerializer.class), "write", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
    paramMethodVisitor.visitInsn(177);
    paramMethodVisitor.visitLabel((Label)localObject1);
    localObject1 = new Label();
    localObject2 = new Label();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "containsReference", "(Ljava/lang/Object;)Z");
    paramMethodVisitor.visitJumpInsn(153, (Label)localObject1);
    paramMethodVisitor.visitVarInsn(25, 0);
    paramMethodVisitor.visitFieldInsn(180, paramContext.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
    paramMethodVisitor.visitJumpInsn(199, (Label)localObject2);
    initNature(paramClass, paramMethodVisitor, paramContext);
    paramMethodVisitor.visitLabel((Label)localObject2);
    paramMethodVisitor.visitVarInsn(25, 0);
    paramMethodVisitor.visitFieldInsn(180, paramContext.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
    paramMethodVisitor.visitVarInsn(25, 1);
    paramMethodVisitor.visitVarInsn(25, 2);
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JavaBeanSerializer.class), "writeReference", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;)V");
    paramMethodVisitor.visitInsn(177);
    paramMethodVisitor.visitLabel((Label)localObject1);
    localObject1 = new Label();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(25, paramContext.paramFieldType());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "isWriteAsArray", "(Ljava/lang/Object;Ljava/lang/reflect/Type;)Z");
    paramMethodVisitor.visitJumpInsn(153, (Label)localObject1);
    paramMethodVisitor.visitVarInsn(25, 0);
    paramMethodVisitor.visitVarInsn(25, 1);
    paramMethodVisitor.visitVarInsn(25, 2);
    paramMethodVisitor.visitVarInsn(25, 3);
    paramMethodVisitor.visitVarInsn(25, 4);
    paramMethodVisitor.visitMethodInsn(182, paramContext.getClassName(), "writeAsArray", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
    paramMethodVisitor.visitInsn(177);
    paramMethodVisitor.visitLabel((Label)localObject1);
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "getContext", "()Lcom/alibaba/fastjson/serializer/SerialContext;");
    paramMethodVisitor.visitVarInsn(58, paramContext.var("parent"));
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("parent"));
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitVarInsn(25, paramContext.paramFieldName());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "setContext", "(Lcom/alibaba/fastjson/serializer/SerialContext;Ljava/lang/Object;Ljava/lang/Object;)V");
    localObject1 = new Label();
    localObject2 = new Label();
    Label localLabel2 = new Label();
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.paramFieldType());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
    paramMethodVisitor.visitJumpInsn(153, (Label)localObject2);
    paramMethodVisitor.visitVarInsn(25, paramContext.paramFieldType());
    paramMethodVisitor.visitVarInsn(25, paramContext.obj());
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(Object.class), "getClass", "()Ljava/lang/Class;");
    paramMethodVisitor.visitJumpInsn(165, (Label)localObject2);
    paramMethodVisitor.visitLabel(localLabel2);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitLdcInsn("{\"" + JSON.DEFAULT_TYPE_KEY + "\":\"" + paramClass.getName() + "\"");
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(Ljava/lang/String;)V");
    paramMethodVisitor.visitVarInsn(16, 44);
    paramMethodVisitor.visitJumpInsn(167, (Label)localObject1);
    paramMethodVisitor.visitLabel((Label)localObject2);
    paramMethodVisitor.visitVarInsn(16, 123);
    paramMethodVisitor.visitLabel((Label)localObject1);
    paramMethodVisitor.visitVarInsn(54, paramContext.var("seperator"));
    _before(paramMethodVisitor, paramContext);
    int i = 0;
    if (i < j)
    {
      localObject1 = (FieldInfo)paramList.get(i);
      localObject2 = ((FieldInfo)localObject1).getFieldClass();
      paramMethodVisitor.visitLdcInsn(((FieldInfo)localObject1).getName());
      paramMethodVisitor.visitVarInsn(58, paramContext.fieldName());
      if (localObject2 == Byte.TYPE)
        _byte(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
      while (true)
      {
        i += 1;
        break;
        if (localObject2 == Short.TYPE)
          _short(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == Integer.TYPE)
          _int(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == Long.TYPE)
          _long(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == Float.TYPE)
          _float(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == Double.TYPE)
          _double(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == Boolean.TYPE)
          _boolean(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == Character.TYPE)
          _char(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == String.class)
          _string(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (localObject2 == BigDecimal.class)
          _decimal(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (List.class.isAssignableFrom((Class)localObject2))
          _list(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else if (((Class)localObject2).isEnum())
          _enum(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
        else
          _object(paramClass, paramMethodVisitor, (FieldInfo)localObject1, paramContext);
      }
    }
    _after(paramMethodVisitor, paramContext);
    paramClass = new Label();
    paramList = new Label();
    paramMethodVisitor.visitVarInsn(21, paramContext.var("seperator"));
    paramMethodVisitor.visitIntInsn(16, 123);
    paramMethodVisitor.visitJumpInsn(160, paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(16, 123);
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
    paramMethodVisitor.visitLabel(paramClass);
    paramMethodVisitor.visitVarInsn(25, paramContext.var("out"));
    paramMethodVisitor.visitVarInsn(16, 125);
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
    paramMethodVisitor.visitLabel(paramList);
    paramMethodVisitor.visitLabel(localLabel1);
    paramMethodVisitor.visitVarInsn(25, paramContext.serializer());
    paramMethodVisitor.visitVarInsn(25, paramContext.var("parent"));
    paramMethodVisitor.visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "setContext", "(Lcom/alibaba/fastjson/serializer/SerialContext;)V");
  }

  private void initNature(Class<?> paramClass, MethodVisitor paramMethodVisitor, Context paramContext)
  {
    paramMethodVisitor.visitVarInsn(25, 0);
    paramMethodVisitor.visitTypeInsn(187, ASMUtils.getType(JavaBeanSerializer.class));
    paramMethodVisitor.visitInsn(89);
    paramMethodVisitor.visitLdcInsn(Type.getType(ASMUtils.getDesc(paramClass)));
    paramMethodVisitor.visitMethodInsn(183, ASMUtils.getType(JavaBeanSerializer.class), "<init>", "(" + ASMUtils.getDesc(Class.class) + ")V");
    paramMethodVisitor.visitFieldInsn(181, paramContext.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
  }

  public ObjectSerializer createJavaBeanSerializer(Class<?> paramClass)
    throws Exception
  {
    return createJavaBeanSerializer(paramClass, (Map)null);
  }

  public ObjectSerializer createJavaBeanSerializer(Class<?> paramClass, Map<String, String> paramMap)
    throws Exception
  {
    if (paramClass.isPrimitive())
      throw new JSONException("unsupportd class " + paramClass.getName());
    Object localObject1 = TypeUtils.computeGetters(paramClass, paramMap, false);
    String str = getGenClassName(paramClass);
    ClassWriter localClassWriter = new ClassWriter();
    localClassWriter.visit(49, 33, str, "java/lang/Object", new String[] { "com/alibaba/fastjson/serializer/ObjectSerializer" });
    localClassWriter.visitField(2, "nature", ASMUtils.getDesc(JavaBeanSerializer.class)).visitEnd();
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (FieldInfo)((Iterator)localObject2).next();
      localClassWriter.visitField(1, ((FieldInfo)localObject3).getName() + "_asm_fieldPrefix", "Ljava/lang/reflect/Type;").visitEnd();
      localClassWriter.visitField(1, ((FieldInfo)localObject3).getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;").visitEnd();
    }
    localObject2 = localClassWriter.visitMethod(1, "<init>", "()V", null, null);
    ((MethodVisitor)localObject2).visitVarInsn(25, 0);
    ((MethodVisitor)localObject2).visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
    Object localObject3 = ((List)localObject1).iterator();
    if (((Iterator)localObject3).hasNext())
    {
      localObject4 = (FieldInfo)((Iterator)localObject3).next();
      ((MethodVisitor)localObject2).visitVarInsn(25, 0);
      ((MethodVisitor)localObject2).visitLdcInsn(Type.getType(ASMUtils.getDesc(((FieldInfo)localObject4).getDeclaringClass())));
      if (((FieldInfo)localObject4).getMethod() != null)
      {
        ((MethodVisitor)localObject2).visitLdcInsn(((FieldInfo)localObject4).getMethod().getName());
        ((MethodVisitor)localObject2).visitMethodInsn(184, ASMUtils.getType(ASMUtils.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
      }
      while (true)
      {
        ((MethodVisitor)localObject2).visitFieldInsn(181, str, ((FieldInfo)localObject4).getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;");
        break;
        ((MethodVisitor)localObject2).visitLdcInsn(((FieldInfo)localObject4).getField().getName());
        ((MethodVisitor)localObject2).visitMethodInsn(184, ASMUtils.getType(ASMUtils.class), "getFieldType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
      }
    }
    ((MethodVisitor)localObject2).visitInsn(177);
    ((MethodVisitor)localObject2).visitMaxs(4, 4);
    ((MethodVisitor)localObject2).visitEnd();
    localObject2 = new Context(str);
    localObject3 = localClassWriter.visitMethod(1, "write", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V", null, new String[] { "java/io/IOException" });
    ((MethodVisitor)localObject3).visitVarInsn(25, ((Context)localObject2).serializer());
    ((MethodVisitor)localObject3).visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "getWriter", "()" + ASMUtils.getDesc(SerializeWriter.class));
    ((MethodVisitor)localObject3).visitVarInsn(58, ((Context)localObject2).var("out"));
    Object localObject4 = (JSONType)paramClass.getAnnotation(JSONType.class);
    if ((localObject4 == null) || (((JSONType)localObject4).alphabetic()))
    {
      localObject4 = new Label();
      ((MethodVisitor)localObject3).visitVarInsn(25, ((Context)localObject2).var("out"));
      ((MethodVisitor)localObject3).visitFieldInsn(178, ASMUtils.getType(SerializerFeature.class), "SortField", "L" + ASMUtils.getType(SerializerFeature.class) + ";");
      ((MethodVisitor)localObject3).visitMethodInsn(182, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + ";" + ")Z");
      ((MethodVisitor)localObject3).visitJumpInsn(153, (Label)localObject4);
      ((MethodVisitor)localObject3).visitVarInsn(25, 0);
      ((MethodVisitor)localObject3).visitVarInsn(25, 1);
      ((MethodVisitor)localObject3).visitVarInsn(25, 2);
      ((MethodVisitor)localObject3).visitVarInsn(25, 3);
      ((MethodVisitor)localObject3).visitVarInsn(25, ((Context)localObject2).paramFieldType());
      ((MethodVisitor)localObject3).visitMethodInsn(182, str, "write1", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
      ((MethodVisitor)localObject3).visitInsn(177);
      ((MethodVisitor)localObject3).visitLabel((Label)localObject4);
    }
    ((MethodVisitor)localObject3).visitVarInsn(25, ((Context)localObject2).obj());
    ((MethodVisitor)localObject3).visitTypeInsn(192, ASMUtils.getType(paramClass));
    ((MethodVisitor)localObject3).visitVarInsn(58, ((Context)localObject2).var("entity"));
    generateWriteMethod(paramClass, (MethodVisitor)localObject3, (List)localObject1, (Context)localObject2);
    ((MethodVisitor)localObject3).visitInsn(177);
    ((MethodVisitor)localObject3).visitMaxs(5, ((Context)localObject2).getVariantCount() + 1);
    ((MethodVisitor)localObject3).visitEnd();
    paramMap = TypeUtils.computeGetters(paramClass, paramMap, true);
    localObject1 = new Context(str);
    localObject2 = localClassWriter.visitMethod(1, "write1", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V", null, new String[] { "java/io/IOException" });
    ((MethodVisitor)localObject2).visitVarInsn(25, ((Context)localObject1).serializer());
    ((MethodVisitor)localObject2).visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "getWriter", "()" + ASMUtils.getDesc(SerializeWriter.class));
    ((MethodVisitor)localObject2).visitVarInsn(58, ((Context)localObject1).var("out"));
    ((MethodVisitor)localObject2).visitVarInsn(25, ((Context)localObject1).obj());
    ((MethodVisitor)localObject2).visitTypeInsn(192, ASMUtils.getType(paramClass));
    ((MethodVisitor)localObject2).visitVarInsn(58, ((Context)localObject1).var("entity"));
    generateWriteMethod(paramClass, (MethodVisitor)localObject2, paramMap, (Context)localObject1);
    ((MethodVisitor)localObject2).visitInsn(177);
    ((MethodVisitor)localObject2).visitMaxs(5, ((Context)localObject1).getVariantCount() + 1);
    ((MethodVisitor)localObject2).visitEnd();
    localObject1 = new Context(str);
    localObject2 = localClassWriter.visitMethod(1, "writeAsArray", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V", null, new String[] { "java/io/IOException" });
    ((MethodVisitor)localObject2).visitVarInsn(25, ((Context)localObject1).serializer());
    ((MethodVisitor)localObject2).visitMethodInsn(182, ASMUtils.getType(JSONSerializer.class), "getWriter", "()" + ASMUtils.getDesc(SerializeWriter.class));
    ((MethodVisitor)localObject2).visitVarInsn(58, ((Context)localObject1).var("out"));
    ((MethodVisitor)localObject2).visitVarInsn(25, ((Context)localObject1).obj());
    ((MethodVisitor)localObject2).visitTypeInsn(192, ASMUtils.getType(paramClass));
    ((MethodVisitor)localObject2).visitVarInsn(58, ((Context)localObject1).var("entity"));
    generateWriteAsArray(paramClass, (MethodVisitor)localObject2, paramMap, (Context)localObject1);
    ((MethodVisitor)localObject2).visitInsn(177);
    ((MethodVisitor)localObject2).visitMaxs(5, ((Context)localObject1).getVariantCount() + 1);
    ((MethodVisitor)localObject2).visitEnd();
    paramClass = localClassWriter.toByteArray();
    return (ObjectSerializer)this.classLoader.defineClassPublic(str, paramClass, 0, paramClass.length).newInstance();
  }

  public String getGenClassName(Class<?> paramClass)
  {
    return "Serializer_" + this.seed.incrementAndGet();
  }

  public boolean isExternalClass(Class<?> paramClass)
  {
    return this.classLoader.isExternalClass(paramClass);
  }

  static class Context
  {
    private final String className;
    private int variantIndex = 8;
    private Map<String, Integer> variants = new HashMap();

    public Context(String paramString)
    {
      this.className = paramString;
    }

    public int fieldName()
    {
      return 5;
    }

    public String getClassName()
    {
      return this.className;
    }

    public int getVariantCount()
    {
      return this.variantIndex;
    }

    public int obj()
    {
      return 2;
    }

    public int original()
    {
      return 6;
    }

    public int paramFieldName()
    {
      return 3;
    }

    public int paramFieldType()
    {
      return 4;
    }

    public int processValue()
    {
      return 7;
    }

    public int serializer()
    {
      return 1;
    }

    public int var(String paramString)
    {
      if ((Integer)this.variants.get(paramString) == null)
      {
        Map localMap = this.variants;
        int i = this.variantIndex;
        this.variantIndex = (i + 1);
        localMap.put(paramString, Integer.valueOf(i));
      }
      return ((Integer)this.variants.get(paramString)).intValue();
    }

    public int var(String paramString, int paramInt)
    {
      if ((Integer)this.variants.get(paramString) == null)
      {
        this.variants.put(paramString, Integer.valueOf(this.variantIndex));
        this.variantIndex += paramInt;
      }
      return ((Integer)this.variants.get(paramString)).intValue();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ASMSerializerFactory
 * JD-Core Version:    0.6.2
 */