import { hasAnyRoles } from "util/auth";
import * as TokenModule from "util/token";
    
describe('hasAnyRoles tests', () => {
    test('should return true when empty list', () => {
        const result = hasAnyRoles([]);
        expect(result).toEqual(true);
    });    

    test('should return true when user has given role', () => {
        jest.spyOn(TokenModule, 'getTokenData').mockReturnValue({
            exp: 0,
            user_name: '',
            authorities: ['ROLE_ADMINISTRADOR', 'ROLE_USUARIO'],
        });

        const result = hasAnyRoles(['ROLE_ADMINISTRADOR']);
        expect(result).toEqual(true);        
    });    

    test('should return false when user does not have given role', () => {
        jest.spyOn(TokenModule, 'getTokenData').mockReturnValue({
            exp: 0,
            user_name: '',
            authorities: ['ROLE_USUARIO'],
        });

        const result = hasAnyRoles(['ROLE_ADMINISTRADOR']);
        expect(result).toEqual(false);  
    }); 
})
