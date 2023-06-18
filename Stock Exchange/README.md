Problem Statement:

    The objective of this project is to design and develop a high-performance, in-memory trading system that accurately emulates the functionalities of a stock exchange. 
    The system should provide a seamless user experience, allowing registered users to effortlessly place, modify, or cancel their orders. Furthermore, it should enable users to retrieve
    the real-time status of their orders without any hassle. To ensure robustness, the system needs to effectively handle synchronization and concurrency in a multi-threaded environment,
    guaranteeing smooth execution even under high loads and concurrent user interactions.

Functional Requirements:

    1. Order Placement and Modification:
        - The system must support registered users in placing buy or sell orders for stocks.
        - Users should have the flexibility to modify their existing orders as needed.
        - For each order, the system must store essential details such as the user ID, order ID, order type (buy/sell), stock symbol (e.g., GOOGLE, META), quantity, price,
          order acceptance timestamp, and order status (ACCEPTED, REJECTED, CANCELED).

    2. Order Cancellation:
        - Users should be able to cancel their open orders at any time.
        - The system should promptly update the status of canceled orders accordingly.

    3. Order Status Query:
        - Users should have the ability to inquire about the current status of their orders.
        - The system should provide comprehensive information about the orders, including their current status and execution details.

    4. Trade Execution:
        - The system should automatically execute trades when compatible buy and sell orders are identified.
        - A trade is considered executed when the buy price matches the sell price of different orders.
        - In the case of multiple eligible orders at the same price, the system should prioritize the oldest orders.
        - For each executed trade, the system should record essential details such as the trade ID, trade type (buy/sell), buyer order ID, seller order ID,
          stock symbol, quantity, price, and trade timestamp.

    5. Concurrency and Synchronization:
        - The system should be designed to handle concurrent order placement, modification, cancellation, and execution efficiently.
        - Appropriate synchronization mechanisms should be implemented to ensure data consistency and prevent conflicts.

    6. Order Book:
        - The system should maintain a separate order book for each stock symbol.
        - The order book should contain all current unexecuted orders, allowing for efficient order matching and execution.

    7. Trade Expiry:
        - The system can incorporate the functionality of automatically canceling trades if they are not executed within a specific time period.

Expectations:

    - The code should be executable and, at a minimum, partially functional.
    - The code should follow a well-structured and properly refactored design, handling exceptions gracefully.
    - All the attributes mentioned in the "Stores" section must be appropriately stored in the code.
    - The code should cover all the functionalities described in the "Supports" section.
    - If time permits, implementing the additional functionality of trade expiry will be considered for extra credit.
