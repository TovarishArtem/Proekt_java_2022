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
        switch (type1) {

            case "АКТ" -> {
                this.typeTask = enumType.ACTIVITIES;
                this.nameTask = "Акт";
            }
            case "УПР" -> {
                this.typeTask = enumType.EXERCISE;
                this.nameTask = "Упр";
            }
            case "ДЗ" -> {
                this.typeTask = enumType.HOMEWORK;
                this.nameTask = "ДЗ";
            }
            case "СЕМ" -> {
                this.typeTask = enumType.SEMESTER;
                this.nameTask = "Сем";
            }
            case "ДОП" -> {
                this.typeTask = enumType.ADDITIONAL;
                this.nameTask = "Доп";
            }
            default -> {
                this.nameTask = "Ошибка";
                throw new IllegalArgumentException("Данный тип заданий отсутствует!");

            }
        }
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

