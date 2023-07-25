interface UserAuth {
    access_token: string | null;
    isLoggedIn: boolean;
}

interface access_token {
    id: string;
    sub: string;
    exp: number;
    iat: number;
    token_type: 0 | 1;
}
