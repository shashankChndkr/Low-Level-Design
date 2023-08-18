Designing a comprehensive parking lot system that adheres to design patterns and SOLID principles requires careful consideration of the various components and interactions within the system. Let's break down the design into different sections, addressing each requirement while incorporating design patterns and SOLID principles:

**1. Multi-Level Parking Infrastructure:**
- Utilize the **Factory Method** design pattern to create different levels of the parking lot, each implementing a common interface.
- Each level should manage its own set of parking spots. Implement the **Composite** pattern to represent the hierarchical structure of floors and parking spots.

**2. Entry and Exit Management:**
- Implement the **Strategy** pattern for payment methods. Create payment strategy classes for cash, credit card, and coupon payments.
- Use the **Command** pattern to encapsulate the request to issue parking tickets, allowing different entry points to utilize the same ticketing process.

**6. Vehicle Type Management:**
- Utilize the **Bridge** pattern to decouple vehicle types from parking spot categories. Each vehicle type can be an abstraction that interacts with the parking spot categories' implementations.


**8. Display of Available Spots:**
- Implement a display board using the **Observer** pattern. Parking levels can be observers that receive updates about available spots from the parking spots.


/////TODO
**3. Payment Flexibility:**
- The **Adapter** pattern can be used to integrate various payment methods into a unified interface for payment processing.
- Implement the **Singleton** pattern for managing payment records and ensuring that payments made on a particular floor are recognized during exit.

**4. Parking Spot Categorization:**
- Apply the **Strategy** pattern to handle different parking spot categories. Each category can be represented by a separate class implementing a common interface.
- Use the **Observer** pattern to notify display boards about changes in available parking spots, ensuring real-time updates.

**5. Parking Spot Division:**
- Implement a **ParkingAllocator** class using the **Strategy** pattern. Different allocation strategies can be defined to optimize space utilization based on demand and usage patterns.

**7. Dynamic Pricing Model:**
- Implement a **PricingStrategy** interface using the **Strategy** pattern. Different pricing strategies can be defined for different time intervals.
- Use the **Template Method** pattern to define a pricing algorithm that calculates fees based on the duration of parking.
