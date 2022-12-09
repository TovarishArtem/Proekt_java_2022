package src.course;

enum enumType {
    ACTIVITIES,
    EXERCISE,
    HOMEWORK,
    SEMESTER,

    ADDITIONAL,
}

public class TypeTask {
    private enumType typeTask;
    private String nameTask;
    public TypeTask(String typeTask) {
        String[] str = typeTask.split(":");

        String type = str[0];
        String type1 = type.toUpperCase();
        if (str.length >= 2){
            switch (type1) {

                case  "АКТ" -> {
                    this.typeTask = enumType.ACTIVITIES;
                    this.nameTask = typeTask;
                }
                case "УПР" -> {
                    this.typeTask = enumType.EXERCISE;
                    this.nameTask = typeTask;
                }
                case "ДЗ" -> {
                    this.typeTask = enumType.HOMEWORK;
                    this.nameTask = typeTask;
                }
                case "СЕМ" -> {
                    this.typeTask = enumType.SEMESTER;
                    this.nameTask = typeTask;
                }
                case "ДОП" -> {
                    this.typeTask = enumType.ADDITIONAL;
                    this.nameTask = typeTask;
                }
                default -> {
                    this.nameTask = null;
                    throw new IllegalArgumentException("Ошибка");
                }
            }
        }
        else this.nameTask = "Ошибка";
    }

    public enumType getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(enumType typeTask) {
        this.typeTask = typeTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}

