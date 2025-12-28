#include <iostream>
#include <iomanip>
#include <vector>
#include <string>
#include <algorithm>
#include <limits>
#include <thread>
#include <chrono>
using namespace std;

// ---------- Colors ----------
#define BLUE  "\033[34m"
#define CYAN  "\033[96m"
#define RESET "\033[0m"

// ---------- Heading ----------
void heading(const string& text) {
    cout << BLUE;
    cout << "\n============================================\n";
    cout << "           " << text << "\n";
    cout << "============================================\n";
    cout << CYAN;
}

// ---------- Typing Effect ----------
void typeEffect(const string& text, int delay = 15) {
    for(char c : text) {
        cout << c;
        cout.flush();
        this_thread::sleep_for(chrono::milliseconds(delay));
    }
    cout << endl;
}

// ---------- Product ----------
template<typename T>
class Product {
public:
    int id;
    string name;
    double price;
    T quantity;

    Product(int i,string n,double p,T q)
        : id(i),name(n),price(p),quantity(q){}
};

// ---------- Cart Item ----------
struct CartItem {
    int id;
    string name;
    double price;
    int quantity;
};

// ---------- Globals ----------
vector<Product<int>> products;
vector<CartItem> cart;
int productCounter = 1;

// ---------- Safe Input ----------
int safeInt(const string& msg) {
    int v;
    while(true){
        cout<<CYAN<<msg<<RESET;
        cin>>v;
        if(cin.fail()||v<=0){
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(),'\n');
            cout<<CYAN<<"Invalid input\n";
        } else break;
    }
    cin.ignore(numeric_limits<streamsize>::max(),'\n');
    return v;
}

double safeDouble(const string& msg){
    double v;
    while(true){
        cout<<CYAN<<msg<<RESET;
        cin>>v;
        if(cin.fail()||v<=0){
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(),'\n');
            cout<<CYAN<<"Invalid input\n";
        } else break;
    }
    cin.ignore(numeric_limits<streamsize>::max(),'\n');
    return v;
}

string safeString(const string& msg){
    string s;
    do{
        cout<<CYAN<<msg<<RESET;
        getline(cin>>ws,s);
    }while(s.empty());
    return s;
}

// ---------- Helpers ----------
Product<int>* findProduct(int id){
    for(auto &p:products)
        if(p.id==id) return &p;
    return nullptr;
}

// ---------- Cart Dashboard ----------
void cartDashboard(){
    while(true){
        heading("CART DASHBOARD");
        typeEffect("1. View Cart\n2. Remove Product\n3. Receipt\n4. Back\n");
        int ch=safeInt("Choice: ");

        if(ch==1){
            if(cart.empty()){
                cout<<CYAN<<"Cart is empty\n";
                continue;
            }
            for(auto &c:cart)
                cout<<"ID:"<<c.id<<" | "<<c.name
                    <<" | Qty:"<<c.quantity
                    <<" | Price:"<<c.price<<"\n";
        }

        else if(ch==2){
            int id=safeInt("Enter Product ID: ");
            auto it=remove_if(cart.begin(),cart.end(),
                [&](CartItem &c){return c.id==id;});
            if(it!=cart.end()){
                cart.erase(it,cart.end());
                cout<<CYAN<<"Removed from cart\n";
            } else cout<<CYAN<<"Not found in cart\n";
        }

        else if(ch==3){
            heading("RECEIPT");
            double total=0;

            // ✅ FINAL STOCK UPDATE HERE
            for(auto &c:cart){
                Product<int>* p=findProduct(c.id);
                if(p && p->quantity>=c.quantity){
                    p->quantity -= c.quantity;
                }
                double sub=c.price*c.quantity;
                total+=sub;
                cout<<c.name<<" x "<<c.quantity<<" = "<<sub<<"\n";
            }

            cout<<"\nTotal: "<<total<<"\n";
            cout<<"E-Commerce App by Talha\n";

            cart.clear(); // cart emptied after checkout
            typeEffect("\nCheckout completed successfully!\n");
        }

        else if(ch==4) return;
    }
}

// ---------- Buyer System ----------
void buySystem(const string& title){
    while(true){
        heading(title);
        typeEffect("1. View Products\n2. Add to Cart\n3. Cart\n4. Back\n");
        int ch=safeInt("Choice: ");

        if(ch==1){
            for(auto &p:products)
                cout<<"ID:"<<p.id<<" | "<<p.name
                    <<" | Price:"<<p.price
                    <<" | Stock:"<<p.quantity<<"\n";
        }

        else if(ch==2){
            int id=safeInt("Product ID: ");
            Product<int>* p=findProduct(id);
            if(!p){
                cout<<CYAN<<"Product not found\n";
                continue;
            }
            int q=safeInt("Quantity: ");
            if(q>p->quantity){
                cout<<CYAN<<"Not enough stock\n";
                continue;
            }

            cart.push_back({p->id,p->name,p->price,q});
            cout<<CYAN<<"Added to cart \n";
        }

        else if(ch==3) cartDashboard();
        else if(ch==4) return;
    }
}

// ---------- Admin ----------
void adminDashboard(){
    while(true){
        heading("ADMIN DASHBOARD");
        typeEffect("1. Add Product\n2. View Products\n3. Delete Product\n4. Back\n");
        int ch=safeInt("Choice: ");

        if(ch==1){
            string n=safeString("Name: ");
            double p=safeDouble("Price: ");
            int q=safeInt("Quantity: ");
            products.emplace_back(productCounter++,n,p,q);
        }

        else if(ch==2){
            for(auto &p:products)
                cout<<"ID:"<<p.id<<" | "<<p.name
                    <<" | Price:"<<p.price
                    <<" | Stock:"<<p.quantity<<"\n";
        }

        else if(ch==3){
            int id=safeInt("Product ID: ");
            auto it=remove_if(products.begin(),products.end(),
                [&](Product<int>&p){return p.id==id;});
            if(it!=products.end()){
                products.erase(it,products.end());
                cout<<CYAN<<"Deleted\n";
            }
        }

        else if(ch==4) return;
    }
}

// ---------- Main ----------
int main(){
    cout<<CYAN;
    while(true){
        heading("CONSOLE E-COMMERCE SYSTEM");
        typeEffect("1. Admin\n2. Seller\n3. Customer\n4. Exit\n");
        int ch=safeInt("Select: ");

        if(ch==1) adminDashboard();
        else if(ch==2) buySystem("SELLER DASHBOARD");
        else if(ch==3) buySystem("CUSTOMER DASHBOARD");
        else if(ch==4) break;
    }
    heading("THANK YOU");
    typeEffect("Thanks for using the system!");
    cout<<RESET;
    return 0;
}
