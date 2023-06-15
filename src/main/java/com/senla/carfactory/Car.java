
// ILineStep interface
interface ILineStep {
    Part buildProductPart();
}

// Part class
class Part {
    private String name;

    public Part(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Car class
class Car {
    private Part body;
    private Part chassis;
    private Part engine;

    public void setBody(Part body) {
        this.body = body;
    }

    public void setChassis(Part chassis) {
        this.chassis = chassis;
    }

    public void setEngine(Part engine) {
        this.engine = engine;
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car Assembly:\n");
        sb.append("Body: ").append(body.getName()).append("\n");
        sb.append("Chassis: ").append(chassis.getName()).append("\n");
        sb.append("Engine: ").append(engine.getName()).append("\n");
        return sb.toString();
    }
}

// AssemblyLine class
class AssemblyLine {
    public Car assembleProduct(ILineStep bodyStep, ILineStep chassisStep, ILineStep engineStep) {
        Car car = new Car();
        car.setBody(bodyStep.buildProductPart());
        car.setChassis(chassisStep.buildProductPart());
        car.setEngine(engineStep.buildProductPart());
        return car;
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        AssemblyLine assemblyLine = new AssemblyLine();

        ILineStep bodyStep = new ILineStep() {
            @Override
            public Part buildProductPart() {
                return new Part("Car Body");
            }
        };

        ILineStep chassisStep = new ILineStep() {
            @Override
            public Part buildProductPart() {
                return new Part("Car Chassis");
            }
        };

        ILineStep engineStep = new ILineStep() {
            @Override
            public Part buildProductPart() {
                return new Part("Car Engine");
            }
        };

        Car assembledCar = assemblyLine.assembleProduct(bodyStep, chassisStep, engineStep);
        System.out.println(assembledCar.getDescription());
    }
}
