import redis

# Redis Lect25
# redis-cli
# Only point in this is to connect to the Redis database

def main():
    r = redis.Redis(host='localhost', port=6379, decode_responses=True)
    r.set('foo', 10)
    print(r.get('foo')) # 10
    r.incr('foo')
    print(r.get('foo')) # 11

if __name__ == '__main__':
    main()